package com.accyln.tictactoe;

import com.accyln.tictactoe.controller.GameController;
import com.accyln.tictactoe.services.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@WebMvcTest(GameController.class)
public class GameControllerTests {
    @Test
    @DisplayName("Testing create player api")
    public void assert_create_player_service(){

    }

}
