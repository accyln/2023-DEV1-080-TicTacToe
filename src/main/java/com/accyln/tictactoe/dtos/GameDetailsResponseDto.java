package com.accyln.tictactoe.dtos;

import com.accyln.tictactoe.entities.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDetailsResponseDto {
    private Long id;
    private Player player1;
    private Player player2;
    private char winner;
}
