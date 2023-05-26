package com.accyln.tictactoe;

import com.accyln.tictactoe.dtos.CreatePlayerRequestDto;
import com.accyln.tictactoe.dtos.CreatePlayersAndGameRequestDto;
import com.accyln.tictactoe.dtos.GameDetailsResponseDto;
import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.testBuilders.TestServiceFactory;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.webjars.NotFoundException;

import java.util.List;

@SpringBootTest
class GameServiceTests {
    @Autowired
    private IGameService gameService;
    @MockBean
    private IGameRepository mockGameRepository;
    @MockBean
    private IPlayerRepository mockPlayerRepository;
    @BeforeEach
    void setup(){
        gameService= TestServiceFactory.getGameService();
    }

    @Test
    @DisplayName("Testing that player created correctly")
    void assert_that_player_created_correctly(){
        Player player1 = gameService.createPlayer(new CreatePlayerRequestDto("Can",'X'));

        Assertions.assertEquals(1,player1.getId());
    }
    @Test
    @DisplayName("Testing that game board created correctly")
    void assert_that_game_board_created_correctly(){
        Player player1 = gameService.getPlayerById(1l);
        Player player2= gameService.getPlayerById(2l);

        Game game=gameService.createGame(player1.getId(),player2.getId());

        Assertions.assertInstanceOf(char[][].class,game.getBoard());
    }

    @Test
    @DisplayName("Testing that game created correctly")
    void assert_that_game_created_correctly(){
        Long playerXId=1l;
        Long playerOId=2l;

        Game game=gameService.createGame(playerXId,playerOId);

        int boardHeight=game.getBoard().length;
        int boardWidth=game.getBoard()[0].length;

        Assertions.assertEquals(1L,game.getPlayer1Id());
        Assertions.assertEquals(2L,game.getPlayer2Id());
        Assertions.assertEquals(3,boardHeight);
        Assertions.assertEquals(3,boardWidth);
    }
    @Test
    @DisplayName("Testing getPlayerById service method")
    void test_getPlayerById(){
        Long playerId=1l;
        Player player=gameService.getPlayerById(playerId);

        Assertions.assertEquals(1l,player.getId());
        Assertions.assertEquals("Can",player.getPlayerName());
    }

    @Test
    @DisplayName("Testing getGameById service method")
    void test_getGameById(){
        Long gameId=1l;
        Game returnedGame=gameService.getGameById(gameId);

        Assertions.assertEquals(1l,returnedGame.getId());
        Assertions.assertEquals(2l,returnedGame.getPlayer2Id());
    }

    @Test
    @DisplayName("Testing getAllGamesWithDetails service method")
    void test_getAllGames(){
        List<GameDetailsResponseDto> gameListResult=gameService.getAllGamesWithDetails();

        GameDetailsResponseDto gameDetailsResponseDto=gameListResult.get(0);

        Assertions.assertEquals(1,gameListResult.size());
        Assertions.assertEquals(GameStatus.ONGOING,gameDetailsResponseDto.getGameStatus());
        Assertions.assertEquals(1l,gameDetailsResponseDto.getPlayer1().getId());


    }

    @Test
    @DisplayName("Testing gameNotFound exception")
    void test_gameNotFound(){
        Assertions.assertThrows(NotFoundException.class,()->gameService.getGameById(3l));
    }

    @Test
    @DisplayName("Testing mekeAmove service")
    void test_makeAmove_service(){
        Game game=gameService.getGameById(1l);
        gameService.makeAmove(game.getId(),0,0,'X');

        Assertions.assertEquals('X',game.getBoard()[0][0]);
        Assertions.assertEquals(1,game.getMoveCount());
    }
    @Test
    @DisplayName("Testing createPlayersAndGame service")
    void test_createPlayersAndGame_service(){
        Game game=gameService.getGameById(1l);
        CreatePlayersAndGameRequestDto createPlayersAndGameRequestDto=
                new CreatePlayersAndGameRequestDto(new CreatePlayerRequestDto("Ahmet",'X'),
                        new CreatePlayerRequestDto("Sare",'O'));
        gameService.createPlayersAndGame(createPlayersAndGameRequestDto);

        Assertions.assertEquals(1l,game.getId());
        Assertions.assertEquals(GameStatus.ONGOING,game.getGameStatus());
        Assertions.assertEquals(1l,game.getPlayer1Id());
    }
}
