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
public class CreatePlayerRequestDto {
    @NotNull(message = "Player name cannot be null")
    private String name;
    @NotNull(message = "Player sign cannot be null")
    private char sign;
}
