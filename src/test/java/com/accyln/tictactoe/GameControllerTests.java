package com.accyln.tictactoe;

import com.accyln.tictactoe.DTOs.CreatePlayerRequestDto;
import com.accyln.tictactoe.controller.GameController;
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
    public void assert_create_player_service() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();
        CreatePlayerRequestDto createPlayerRequestDto= new CreatePlayerRequestDto("Ahmet",'X');
        String request=objectMapper.writeValueAsString(createPlayerRequestDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/game/newPlayer").accept(MediaType.APPLICATION_JSON)
                .content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

}
