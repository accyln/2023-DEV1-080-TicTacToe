package com.accyln.tictactoe.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Slf4j
public class GameAlreadyFinishedException extends BaseRuntimeException {
    public GameAlreadyFinishedException(String message){
        super(message, HttpStatus.BAD_REQUEST);
        log.error(message);
    }
}