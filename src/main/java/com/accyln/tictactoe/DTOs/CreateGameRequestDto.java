package com.accyln.tictactoe.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateGameRequestDto {
    @NotNull(message = "Player1 id cannot be null")
    private Long player1Id;
    @NotNull(message = "Player2 id cannot be null")
    private Long player2Id;
}
