package com.accyln.tictactoe.DTOs;

import com.accyln.tictactoe.entities.Player;

public class GameDetailsResponseDto {
    private Long id;
    private Player player1;
    private Player player2;
    private char winner;
}