package com.accyln.tictactoe.exceptions;

import org.springframework.http.HttpStatus;

public class GameAlreadyFinishedException extends BaseRuntimeException {
    public GameAlreadyFinishedException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
