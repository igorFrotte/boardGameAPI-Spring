package com.board.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.board.api.exceptions.clientExceptions.ClientConflictCPFException;
import com.board.api.exceptions.clientExceptions.ClientNotFoundException;
import com.board.api.exceptions.gameExceptions.GameConflictNameException;
import com.board.api.exceptions.gameExceptions.GameNotFoundException;
import com.board.api.exceptions.rentalExceptions.RentalAlreadyCompletedException;
import com.board.api.exceptions.rentalExceptions.RentalNotFoundException;
import com.board.api.exceptions.rentalExceptions.RentalStockNotAvailableException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<String> handlerGameNotFound(GameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({GameConflictNameException.class})
    public ResponseEntity<String> handlerGameConflictName(GameConflictNameException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<String> handlerCustomerNotFound(ClientNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ClientConflictCPFException.class})
    public ResponseEntity<String> handlerCustomerConflictCPF(ClientConflictCPFException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({RentalNotFoundException.class})
    public ResponseEntity<String> handlerRentalNotFound(RentalNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({RentalStockNotAvailableException.class})
    public ResponseEntity<String> handlerRentalNotAvailable(RentalStockNotAvailableException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler({RentalAlreadyCompletedException.class})
    public ResponseEntity<String> handlerRentalAlreadyCompleted(RentalAlreadyCompletedException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

}