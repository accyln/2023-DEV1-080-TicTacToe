package com.accyln.tictactoe.mockRepositories;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import com.accyln.tictactoe.repositories.IGameRepository;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public final class MockGameRepository {
    public static IGameRepository getMockGameRepository(){
        IGameRepository mockGameRepository = mock(IGameRepository.class);
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Game game=new Game(player1.getId(),player2.getId());
        game.setId(1l);
        Mockito.when(mockGameRepository.findById(1l)).thenReturn(Optional.of(game));
        Mockito.when(mockGameRepository.save(Mockito.any(Game.class))).thenReturn(game);
        Mockito.when(mockGameRepository.findAll()).thenReturn(Arrays.asList(game));

        return mockGameRepository;
    }
}
