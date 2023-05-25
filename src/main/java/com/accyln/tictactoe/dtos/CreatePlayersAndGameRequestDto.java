package com.accyln.tictactoe.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePlayersAndGameRequestDto {
    @NotNull(message = "Player1 cannot be null")
    private CreatePlayerRequestDto player1;
    @NotNull(message = "Player2 cannot be null")
    private CreatePlayerRequestDto player2;
}
