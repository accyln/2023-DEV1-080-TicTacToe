package com.accyln.tictactoe.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {
    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ErrorResponse> controlledGeneralHandler(BaseRuntimeException exception) {
        HttpStatus status = exception.getStatus();
        ErrorResponse error = new ErrorResponse(status.value(), new Date(), exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalHandler(Exception exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
