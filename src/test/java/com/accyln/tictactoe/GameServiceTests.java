package com.accyln.tictactoe;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import com.accyln.tictactoe.services.GameService;
import com.accyln.tictactoe.services.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
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
    @Test
    @DisplayName("Testing getPlayerById service method")
    public void test_getPlayerById(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);

        Mockito.when(mockPlayerRepository.findById(1l)).thenReturn(Optional.of(player1));
        gameService=new GameService(mockGameRepository,mockPlayerRepository);
        Player player=gameService.getPlayerById(1l);

        Assertions.assertEquals(1l,player.getId());
        Assertions.assertEquals("Can",player.getPlayerName());
    }

    @Test
    @DisplayName("Testing getGameById service method")
    public void test_getGameById(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);

        Mockito.when(mockGameRepository.findById(1l)).thenReturn(Optional.of(game));
        gameService=new GameService(mockGameRepository,mockPlayerRepository);
        Game returnedGame=gameService.getGameById(1l);

        Assertions.assertEquals(1l,returnedGame.getId());
        Assertions.assertEquals(2l,returnedGame.getPlayer2_id());
        Assertions.assertEquals(0,returnedGame.getMoveCount());
    }

    @Test
    @DisplayName("Testing getAllGames service method")
    public void test_getAllGames(){
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        List<Game> gameList=new ArrayList<>();
        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        gameList.add(game);

        Mockito.when(mockGameRepository.findAll()).thenReturn(gameList);
        gameService=new GameService(mockGameRepository,mockPlayerRepository);
        List<Game> gameListResult=gameService.getAllGames();

        Assertions.assertEquals(1,gameListResult.size());
    }
}
