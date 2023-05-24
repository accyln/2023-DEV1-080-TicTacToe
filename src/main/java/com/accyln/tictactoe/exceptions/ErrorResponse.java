package com.accyln.tictactoe.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
}
