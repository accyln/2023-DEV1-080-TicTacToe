package com.accyln.tictactoe.dtos;

import com.accyln.tictactoe.entities.Player;
import com.accyln.tictactoe.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GameDetailsResponseDto {
    private Long id;
    private Player player1;
    private Player player2;
    private char winner;
    private GameStatus gameStatus;
}
