package com.accyln.tictactoe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePlayerRequestDto {
    private String name;
    private char sign;
}
