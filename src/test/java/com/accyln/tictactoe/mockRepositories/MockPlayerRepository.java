package com.accyln.tictactoe.mockRepositories;

import com.accyln.tictactoe.entities.Game;
import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.repositories.IGameRepository;
import com.accyln.tictactoe.repositories.IPlayerRepository;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public final class MockPlayerRepository {
    public static IPlayerRepository getMockPlayerRepository(){
        IPlayerRepository mockPlayerRepository = mock(IPlayerRepository.class);
        Player player1 = new Player(1l,"Can",'X');
        Player player2= new Player(2l,"Sarah",'O');

        Mockito.when(mockPlayerRepository.findById(1l)).thenReturn(Optional.of(player1));
        Mockito.when(mockPlayerRepository.findById(2l)).thenReturn(Optional.of(player2));
        Mockito.when(mockPlayerRepository.save(Mockito.any(Player.class))).thenReturn(player1);

        return mockPlayerRepository;
    }
}
