package com.accyln.tictactoe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateGameRequestDto {
    private Long player1Id;
    private Long player2Id;
}
