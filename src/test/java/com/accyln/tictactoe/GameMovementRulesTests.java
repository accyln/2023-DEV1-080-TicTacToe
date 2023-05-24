package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.exceptions.GameAlreadyFinishedException;
import com.accyln.tictactoe.exceptions.InvalidFirstMovingPlayerSignException;
import com.accyln.tictactoe.exceptions.SamePlayerCannotSignInSuccesion;
import com.accyln.tictactoe.exceptions.SquareAlreadyTakenException;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.webjars.NotFoundException;

import java.util.Optional;

@SpringBootTest
public class GameMovementRulesTests {
    @Autowired
    private IGameService gameService;
    @MockBean
    private IGameRepository mockGameRepository;
    @MockBean
    private IPlayerRepository mockPlayerRepository;
    @BeforeEach
    void setup(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');
        Mockito.when(mockPlayerRepository.findById(1l)).thenReturn(Optional.of(player1));
        Mockito.when(mockPlayerRepository.findById(2l)).thenReturn(Optional.of(player2));

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        Mockito.when(mockGameRepository.findById(1l)).thenReturn(Optional.of(game));

        gameService=new GameService(mockGameRepository,mockPlayerRepository);

    }
    @Test
    @DisplayName("Testing that player X makes first move")
    public void assert_that_x_moves_first(){
        Long gameId=1l;
        char playingSign='X';
        int rowId=0;
        int colId=0;
        Game game=gameService.getGameById(gameId);
        gameService.makeAmove(game.getId(),rowId,colId,playingSign);

        Assertions.assertEquals('X',game.getBoard()[rowId][colId]);
        Assertions.assertEquals('X',game.getLastPlayedSign());
    }

    @Test
    @DisplayName("Testing that player O cannot makes first move")
    public void assert_that_o_cannot_moves_first(){
        Long gameId=1l;
        int rowId=0;
        int colId=0;
        Game game=gameService.getGameById(gameId);
        Assertions.assertThrows(InvalidFirstMovingPlayerSignException.class,
                ()->gameService.makeAmove(game.getId(),rowId,colId,'O'));
    }

    @Test
    @DisplayName("Testing that players cannot play on a played position")
    public void assert_that_players_cannot_play_on_a_played_position(){
        Long gameId=1l;
        int rowId=0;
        int colId=0;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),rowId,colId,'X');

        Assertions.assertThrows( SquareAlreadyTakenException.class,
                ()->gameService.makeAmove(game.getId(),rowId,colId, 'O'));
    }

    @Test
    @DisplayName("Testing that players cannot sign in succession")
    public void assert_same_player_cannot_make_a_move_in_succession(){
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        int firstMoveRowId=0;
        int firstMoveColId=0;
        int secondMoveRowId=0;
        int secondMoveColId=1;
        gameService.makeAmove(game.getId(),firstMoveRowId,firstMoveColId,'X');

        Assertions.assertThrows( SamePlayerCannotSignInSuccesion.class,
                ()->gameService.makeAmove(game.getId(),secondMoveRowId,secondMoveColId, 'X'));
    }

    @Test
    @DisplayName("Testing that making a move signed correct place on the board")
    public void assert_that_correct_square_signed_on_the_board(){
        Long gameId=1l;
        int rowId=0;
        int colId=0;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),rowId,colId,'X');//making a move

        Assertions.assertEquals('X',game.getBoard()[rowId][colId]);//assert that right square signed
    }

    @Test
    @DisplayName("Testing that cannot make a move after game finished")
    public void assert_cannot_make_a_move_after_game_finished(){
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),1,1, 'O');
        gameService.makeAmove(game.getId(),0,1, 'X');
        gameService.makeAmove(game.getId(),2,0, 'O');
        gameService.makeAmove(game.getId(),0,2, 'X');

        Assertions.assertThrows(GameAlreadyFinishedException.class,()->gameService.makeAmove(game.getId(),2,2, 'O'));
    }
}