package com.accyln.tictactoe;

import com.accyln.tictactoe.DTOs.CreateGameRequestDto;
import com.accyln.tictactoe.DTOs.CreatePlayerRequestDto;
import com.accyln.tictactoe.DTOs.MakeAMoveRequestDto;
import com.accyln.tictactoe.controller.GameController;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.helpers.CalculateWinnerHelper;
import com.accyln.tictactoe.helpers.CheckGameRulesHelper;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTests {
    @MockBean
    private IGameService gameService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IGameRepository mockGameRepository;
    @MockBean
    private IPlayerRepository mockPlayerRepository;
    @BeforeEach
    void setup(){
        //mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();

        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');
        Mockito.when(mockPlayerRepository.findById(1l)).thenReturn(Optional.of(player1));
        Mockito.when(mockPlayerRepository.findById(2l)).thenReturn(Optional.of(player2));
        Mockito.when(mockPlayerRepository.save(Mockito.any(Player.class))).thenReturn(player1);

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        Mockito.when(mockGameRepository.findById(1l)).thenReturn(Optional.of(game));
        Mockito.when(mockGameRepository.save(Mockito.any(Game.class))).thenReturn(game);
        Mockito.when(mockGameRepository.findAll()).thenReturn(Arrays.asList(game));

        CalculateWinnerHelper calculateWinnerHelper=new CalculateWinnerHelper();
        CheckGameRulesHelper checkGameRulesHelper=new CheckGameRulesHelper();
        gameService=new GameService(mockGameRepository,mockPlayerRepository,calculateWinnerHelper,checkGameRulesHelper);
    }
    @Test
    @DisplayName("Testing createPlayer endpoint")
    public void test_createPlayer_endpoint() throws Exception{
        CreatePlayerRequestDto createPlayerRequestDto= new CreatePlayerRequestDto("Ahmet",'X');
        String request=objectMapper.writeValueAsString(createPlayerRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newPlayer").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Testing createGame endpoint")
    public void test_createGame_endpoint() throws Exception{
        CreateGameRequestDto createGameRequestDto= new CreateGameRequestDto(1l,2l);
        String request=objectMapper.writeValueAsString(createGameRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newGame").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }
    @Test
    @DisplayName("Testing makeAmove endpoint")
    public void test_makeAmove_endpoint() throws Exception{
        Long gameId=1l;
        int rowId=0;
        int colId=0;
        char sign='X';
        Game game=gameService.getGameById(gameId);
        MakeAMoveRequestDto makeAMoveRequestDto= new MakeAMoveRequestDto(game.getId(),rowId,colId,sign);
        String request=objectMapper.writeValueAsString(makeAMoveRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/makeAmove").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
    @Test
    @DisplayName("Testing getPlayerById endpoint")
    public void test_getPlayerById_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getPlayerById?playerId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Testing getGameById endpoint")
    public void test_getGameById_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getGameById?gameId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing getAllGames endpoint")
    public void test_getAllGames_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getAllGames")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing controlled exception")
    public void test_uncontrolled_generalException_response() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/makeAmove")
                .content("wrong request").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
    }
}