package com.accyln.tictactoe.exceptions;

import org.springframework.http.HttpStatus;

public class SamePlayerCannotSignInSuccesion extends BaseRuntimeException{
    public SamePlayerCannotSignInSuccesion(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
