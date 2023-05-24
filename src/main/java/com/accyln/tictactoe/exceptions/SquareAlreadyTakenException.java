package com.accyln.tictactoe.exceptions;

import org.springframework.http.HttpStatus;

public class SquareAlreadyTakenException extends BaseRuntimeException {
    public SquareAlreadyTakenException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
