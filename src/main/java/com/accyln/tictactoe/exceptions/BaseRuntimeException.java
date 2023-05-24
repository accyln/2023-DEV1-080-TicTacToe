package com.accyln.tictactoe.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseRuntimeException extends RuntimeException{
    private HttpStatus status;
    public BaseRuntimeException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
