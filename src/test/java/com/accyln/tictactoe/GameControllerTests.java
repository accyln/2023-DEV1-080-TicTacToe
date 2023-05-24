package com.accyln.tictactoe;

import com.accyln.tictactoe.dtos.CreateGameRequestDto;
import com.accyln.tictactoe.dtos.CreatePlayerRequestDto;
import com.accyln.tictactoe.dtos.CreatePlayersAndGameRequestDto;
import com.accyln.tictactoe.dtos.MakeAMoveRequestDto;
import com.accyln.tictactoe.controller.GameController;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.testBuilders.TestServiceFactory;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.IGameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTests {
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
        gameService= TestServiceFactory.getGameService();
    }
    @Test
    @DisplayName("Testing createPlayer endpoint")
    void test_createPlayer_endpoint() throws Exception{
        CreatePlayerRequestDto createPlayerRequestDto= new CreatePlayerRequestDto("Ahmet",'X');
        String request=objectMapper.writeValueAsString(createPlayerRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newPlayer").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
    @Test
    @DisplayName("Testing createGame endpoint")
    void test_createGame_endpoint() throws Exception{
        CreateGameRequestDto createGameRequestDto= new CreateGameRequestDto(1l,2l);
        String request=objectMapper.writeValueAsString(createGameRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newGame").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }
    @Test
    @DisplayName("Testing createPlayersAndGame endpoint")
    void test_createPlayersAndGame_endpoint() throws Exception{
        CreatePlayersAndGameRequestDto createPlayersAndGameRequestDto=
                new CreatePlayersAndGameRequestDto(new CreatePlayerRequestDto("Can",'X'),
                                                   new CreatePlayerRequestDto("Sare",'O'));
        String request=objectMapper.writeValueAsString(createPlayersAndGameRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/createPlayersAndGame").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }
    @Test
    @DisplayName("Testing makeAmove endpoint")
    void test_makeAmove_endpoint() throws Exception{
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
    void test_getPlayerById_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getPlayerById?playerId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Testing getGameById endpoint")
    void test_getGameById_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getGameById?gameId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing getAllGamesWithDetails endpoint")
    void test_getAllGamesWithDetails_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/game/getAllGamesWithDetails")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing controlled exception")
    void test_uncontrolled_generalException_response() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/makeAmove")
                .content("wrong request").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}