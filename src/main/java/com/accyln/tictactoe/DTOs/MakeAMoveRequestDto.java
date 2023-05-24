package com.accyln.tictactoe.DTOs;

import com.accyln.tictactoe.entities.Game;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MakeAMoveRequestDto {
    @NotNull(message = "Game id cannot be null")
    private Long gameId;
    @NotNull(message = "Row id cannot be null")
    private int rowId;
    @NotNull(message = "Col id cannot be null")
    private int colId;
    @NotNull(message = "Sign cannot be null")
    private char sign;

}
