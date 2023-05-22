package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.services.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameMovementRulesTests {
    @Test
    @DisplayName("Testing that player X makes first move")
    public void assert_that_x_moves_first(){
        Player player1=new Player('X');
        Player player2= new Player('O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        int rowId=0;
        int colId=0;

        GameService gameService= new GameService();
        gameService.makeAmove(game,rowId,colId,player1.getSign());

        Assertions.assertEquals('X',game.getBoard()[0][0]);
        Assertions.assertEquals('X',game.getLastPlayedSign());
    }
    
}
