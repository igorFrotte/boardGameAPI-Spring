package com.board.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.board.api.exceptions.clientExceptions.ClientConflictCPFException;
import com.board.api.exceptions.clientExceptions.ClientNotFoundException;
import com.board.api.exceptions.gameExceptions.GameConflictNameException;
import com.board.api.exceptions.gameExceptions.GameNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<String> handlerGameNotFound(GameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({GameConflictNameException.class})
    public ResponseEntity<String> handlerGameNameConflict(GameConflictNameException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<String> handlerCustomerNotFound(ClientNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ClientConflictCPFException.class})
    public ResponseEntity<String> handlerCustomerCpfConflict(ClientConflictCPFException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

}