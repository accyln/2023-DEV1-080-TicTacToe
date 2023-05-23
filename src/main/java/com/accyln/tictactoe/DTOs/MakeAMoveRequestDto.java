package com.accyln.tictactoe.DTOs;

import com.accyln.tictactoe.entities.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MakeAMoveRequestDto {
    private Game game;
    private int rowId;
    private int colId;
    private char sign;

}
