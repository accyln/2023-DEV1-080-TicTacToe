package com.accyln.tictactoe.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFirstMovingPlayerSignException extends BaseRuntimeException {
    public InvalidFirstMovingPlayerSignException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
