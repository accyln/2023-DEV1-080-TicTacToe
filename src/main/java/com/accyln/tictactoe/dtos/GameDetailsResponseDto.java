package com.accyln.tictactoe.dtos;

import com.accyln.tictactoe.entities.Player;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class GameDetailsResponseDto {
    private Long id;
    private Player player1;
    private Player player2;
    private char winner;
}
