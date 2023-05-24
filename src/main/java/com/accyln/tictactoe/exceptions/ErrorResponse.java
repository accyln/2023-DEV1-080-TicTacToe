package com.accyln.tictactoe.exceptions;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
}
