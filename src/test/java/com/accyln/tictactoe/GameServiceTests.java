package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GameServiceTests {
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
        Mockito.when(mockPlayerRepository.save(Mockito.any(Player.class))).thenReturn(player1);

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        Mockito.when(mockGameRepository.findById(1l)).thenReturn(Optional.of(game));
        Mockito.when(mockGameRepository.save(Mockito.any(Game.class))).thenReturn(game);
        Mockito.when(mockGameRepository.findAll()).thenReturn(Arrays.asList(game));

        gameService=new GameService(mockGameRepository,mockPlayerRepository);
    }

    @Test
    @DisplayName("Testing that player created correctly")
    public void assert_that_player_created_correctly(){
        Player player1 = gameService.createPlayer("Can",'X');

        Assertions.assertEquals(1,player1.getId());
    }
    @Test
    @DisplayName("Testing that game board created correctly")
    public void assert_that_game_board_created_correctly(){
        Player player1 = gameService.getPlayerById(1l);
        Player player2= gameService.getPlayerById(2l);

        Game game=gameService.createGame(player1.getId(),player2.getId());

        Assertions.assertInstanceOf(char[][].class,game.getBoard());
    }

    @Test
    @DisplayName("Testing that game created correctly")
    public void assert_that_game_created_correctly(){
        Long playerXId=1l;
        Long playerOId=2l;

        Game game=gameService.createGame(playerXId,playerOId);

        int boardHeight=game.getBoard().length;
        int boardWidth=game.getBoard()[0].length;

        Assertions.assertEquals(1L,game.getPlayer1_id());
        Assertions.assertEquals(2L,game.getPlayer2_id());
        Assertions.assertEquals(3,boardHeight);
        Assertions.assertEquals(3,boardWidth);
    }
    @Test
    @DisplayName("Testing getPlayerById service method")
    public void test_getPlayerById(){
        Long playerId=1l;
        Player player=gameService.getPlayerById(playerId);

        Assertions.assertEquals(1l,player.getId());
        Assertions.assertEquals("Can",player.getPlayerName());
    }

    @Test
    @DisplayName("Testing getGameById service method")
    public void test_getGameById(){
        Long gameId=1l;
        Game returnedGame=gameService.getGameById(gameId);

        Assertions.assertEquals(1l,returnedGame.getId());
        Assertions.assertEquals(2l,returnedGame.getPlayer2_id());
    }

    @Test
    @DisplayName("Testing getAllGames service method")
    public void test_getAllGames(){
        List<Game> gameListResult=gameService.getAllGames();

        Assertions.assertEquals(1,gameListResult.size());
    }

    @Test
    @DisplayName("Testing getAllGames service method")
    public void test_gameNotFound(){
        Assertions.assertThrows(NotFoundException.class,()->gameService.getGameById(3l));
    }
}
