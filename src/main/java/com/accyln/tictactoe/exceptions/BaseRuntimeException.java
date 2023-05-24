package com.accyln.tictactoe.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRuntimeException extends RuntimeException{
    private final HttpStatus status;
    public BaseRuntimeException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
