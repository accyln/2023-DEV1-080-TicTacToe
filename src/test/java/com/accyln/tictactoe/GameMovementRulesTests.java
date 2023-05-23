package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.exceptions.SamePlayerCannotSignInSuccesion;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameMovementRulesTests {
    @Autowired
    private IGameService gameService;
    @Test
    @DisplayName("Testing that player X makes first move")
    public void assert_that_x_moves_first(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        int rowId=0;
        int colId=0;

        gameService.makeAmove(game,rowId,colId,player1.getPlayerSign());

        Assertions.assertEquals('X',game.getBoard()[0][0]);
        Assertions.assertEquals('X',game.getLastPlayedSign());
    }

    @Test
    @DisplayName("Testing that players cannot play on a played position")
    public void assert_that_players_cannot_play_on_a_played_position(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        int rowId=0;
        int colId=0;

        gameService.makeAmove(game,rowId,colId,player1.getPlayerSign());

        Assertions.assertThrows( SquareAlreadyTakenException.class,()->gameService.makeAmove(game,rowId,colId, player2.getPlayerSign()));
    }

    @Test
    @DisplayName("Testing that players cannot sign in succession")
    public void assert_same_player_cannot_make_a_move_in_succession(){
        Player player1 = new Player(1l,"Ahmet",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);

        int firstMoveRowId=0;
        int firstMoveColId=0;
        int secondMoveRowId=0;
        int secondMoveColId=1;
        gameService.makeAmove(game,firstMoveRowId,firstMoveColId,player1.getPlayerSign());

        Assertions.assertThrows( SamePlayerCannotSignInSuccesion.class,()->gameService.makeAmove(game,secondMoveRowId,secondMoveColId, player1.getPlayerSign()));
    }

    @Test
    @DisplayName("Testing that making a move signed correct place on the board")
    public void assert_that_correct_square_signed_on_the_board(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');
        int rowId=1;
        int colId=1;
        Game game=gameService.createGame(player1.getId(),player2.getId());
        gameService.makeAmove(game,rowId,colId,player1.getPlayerSign());

        Assertions.assertEquals('X',game.getBoard()[rowId][colId]);
    }


}
