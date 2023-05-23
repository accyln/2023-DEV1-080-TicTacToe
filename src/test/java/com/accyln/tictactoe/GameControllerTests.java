package com.accyln.tictactoe;

import com.accyln.tictactoe.DTOs.CreateGameRequestDto;
import com.accyln.tictactoe.DTOs.CreatePlayerRequestDto;
import com.accyln.tictactoe.DTOs.MakeAMoveRequestDto;
import com.accyln.tictactoe.controller.GameController;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTests {
    @MockBean
    private IGameService gameService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("Testing create player api")
    public void test_create_player_service() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();
        CreatePlayerRequestDto createPlayerRequestDto= new CreatePlayerRequestDto("Ahmet",'X');
        String request=objectMapper.writeValueAsString(createPlayerRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newPlayer").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Testing create a new game api")
    public void test_create_game_service() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();
        CreateGameRequestDto createGameRequestDto= new CreateGameRequestDto(1l,2l);
        String request=objectMapper.writeValueAsString(createGameRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newGame").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Testing that make a move service")
    public void test_makeAmove_service() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();
        Player player1=new Player(1l,"Ahmet",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=gameService.createGame(player1.getId(),player2.getId());
        int rowId=0;
        int colId=0;
        char sign='X';

        MakeAMoveRequestDto makeAMoveRequestDto= new MakeAMoveRequestDto(game,rowId,colId,sign);
        String request=objectMapper.writeValueAsString(makeAMoveRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/makeAmove").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

}
