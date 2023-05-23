package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameCreationTests {

    @Autowired
    private IGameService gameService;
    @Test
    @DisplayName("Testing that game board created correctly")
    public void assert_that_game_board_created_correctly(){
        Player player1 = new Player('X');
        player1.setId(1l);
        Player player2= new Player('O');
        player2.setId(2l);

        Game game=gameService.createGame(player1.getId(),player2.getId());

        Assertions.assertInstanceOf(char[][].class,game.getBoard());
    }

    @Test
    @DisplayName("Testing that game created correctly")
    public void assert_that_game_created_correctly(){
        Player player1 = new Player('X');
        player1.setId(1l);
        Player player2= new Player('O');
        player2.setId(2l);

        Game game=gameService.createGame(player1.getId(),player2.getId());

        Assertions.assertEquals(1L,game.getPlayer1_id());
        Assertions.assertEquals(2L,game.getPlayer2_id());
        Assertions.assertEquals(3,game.getBoard().length);
    }
}
