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
public class GameResultTests {
    @Autowired
    private IGameService gameService;
    @Test
    @DisplayName("Testing that X won the game")
    public void recognise_x_has_won() {
        Player player1=new Player(1l,"Ahmet",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=gameService.createGame(player1.getId(),player2.getId());

        gameService.makeAmove(game,0,0, player1.getSign());
        gameService.makeAmove(game,1,1, player2.getSign());
        gameService.makeAmove(game,0,1, player1.getSign());
        gameService.makeAmove(game,2,0, player2.getSign());
        gameService.makeAmove(game,0,2, player1.getSign());

        Assertions.assertEquals("ENDED",game.getGameStatus());
        Assertions.assertEquals('X',game.getWinner());

    }

    @Test
    @DisplayName("Testing that O won the game")
    public void recognise_o_has_won() {
        Player player1=new Player(1l,"Ahmet",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=gameService.createGame(player1.getId(),player2.getId());

        gameService.makeAmove(game,0,0, player1.getSign());
        gameService.makeAmove(game,0,2, player2.getSign());
        gameService.makeAmove(game,2,1, player1.getSign());
        gameService.makeAmove(game,1,1, player2.getSign());
        gameService.makeAmove(game,1,0, player1.getSign());
        gameService.makeAmove(game,2,0, player2.getSign());

        Assertions.assertEquals("ENDED",game.getGameStatus());
        Assertions.assertEquals('O',game.getWinner());
    }

    @Test
    @DisplayName("Testing that game finished as draw")
    public void recognise_drawn() {
        Player player1=new Player(1l,"Ahmet",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=gameService.createGame(player1.getId(),player2.getId());

        gameService.makeAmove(game,0,0, player1.getSign());
        gameService.makeAmove(game,2,0, player2.getSign());
        gameService.makeAmove(game,1,0, player1.getSign());
        gameService.makeAmove(game,1,1, player2.getSign());
        gameService.makeAmove(game,0,2, player1.getSign());
        gameService.makeAmove(game,0,1, player2.getSign());
        gameService.makeAmove(game,2,1, player1.getSign());
        gameService.makeAmove(game,2,2, player2.getSign());
        gameService.makeAmove(game,1,2, player1.getSign());

        Assertions.assertEquals("ENDED",game.getGameStatus());
        Assertions.assertEquals(9,game.getMoveCount());
    }
}
