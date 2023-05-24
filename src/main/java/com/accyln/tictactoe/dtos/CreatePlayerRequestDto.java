package com.accyln.tictactoe.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePlayerRequestDto {
    @NotNull(message = "Player name cannot be null")
    private String name;
    @NotNull(message = "Player sign cannot be null")
    private char sign;
}
