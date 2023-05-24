package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.helpers.CalculateWinnerHelper;
import com.accyln.tictactoe.helpers.CheckGameRulesHelper;
import com.accyln.tictactoe.mockServices.MockServiceFactory;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class GameResultTests {
    @Autowired
    private IGameService gameService;
    @MockBean
    private IGameRepository mockGameRepository;
    @MockBean
    private IPlayerRepository mockPlayerRepository;
    @BeforeEach
    void setup(){
        gameService= MockServiceFactory.getMockGameService();
    }
    @Test
    @DisplayName("Testing that X won the game")
    public void recognise_x_has_won() {
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),1,1, 'O');
        gameService.makeAmove(game.getId(),0,1, 'X');
        gameService.makeAmove(game.getId(),2,0, 'O');
        gameService.makeAmove(game.getId(),0,2, 'X');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals('X',game.getWinner());

    }

    @Test
    @DisplayName("Testing that O won the game")
    public void recognise_o_has_won() {
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),0,2, 'O');
        gameService.makeAmove(game.getId(),2,1, 'X');
        gameService.makeAmove(game.getId(),1,1, 'O');
        gameService.makeAmove(game.getId(),1,0, 'X');
        gameService.makeAmove(game.getId(),2,0, 'O');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals('O',game.getWinner());
    }

    @Test
    @DisplayName("Testing that game finished as draw")
    public void recognise_drawn() {
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),2,0, 'O');
        gameService.makeAmove(game.getId(),1,0, 'X');
        gameService.makeAmove(game.getId(),1,1, 'O');
        gameService.makeAmove(game.getId(),0,2, 'X');
        gameService.makeAmove(game.getId(),0,1, 'O');
        gameService.makeAmove(game.getId(),2,1, 'X');
        gameService.makeAmove(game.getId(),2,2, 'O');
        gameService.makeAmove(game.getId(),1,2, 'X');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals(9,game.getMoveCount());
    }
    @Test
    @DisplayName("Testing that one player has won with make a sign three in a row horizontally.")
    public void recognise_horizontally_won() {
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),1,0, 'X');
        gameService.makeAmove(game.getId(),0,1, 'O');
        gameService.makeAmove(game.getId(),1,1, 'X');
        gameService.makeAmove(game.getId(),2,0, 'O');
        gameService.makeAmove(game.getId(),1,2, 'X');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals('X',game.getWinner());

    }

    @Test
    @DisplayName("Testing that one player has won with make a sign three in a row vertically.")
    public void recognise_vertically_won() {
        Game game= gameService.getGameById(1l);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),1,1, 'O');
        gameService.makeAmove(game.getId(),1,0, 'X');
        gameService.makeAmove(game.getId(),2,1, 'O');
        gameService.makeAmove(game.getId(),2,0, 'X');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals('X',game.getWinner());

    }

    @Test
    @DisplayName("Testing that one player has won with make a sign three in a row diagonally.")
    public void recognise_diagonally_won() {
        Long gameId=1l;
        Game game=gameService.getGameById(gameId);

        gameService.makeAmove(game.getId(),0,0, 'X');
        gameService.makeAmove(game.getId(),0,1, 'O');
        gameService.makeAmove(game.getId(),1,1, 'X');
        gameService.makeAmove(game.getId(),2,1, 'O');
        gameService.makeAmove(game.getId(),2,2, 'X');

        Assertions.assertEquals(GameStatus.ENDED,game.getGameStatus());
        Assertions.assertEquals('X',game.getWinner());

    }
}
