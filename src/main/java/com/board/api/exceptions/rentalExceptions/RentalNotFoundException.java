package com.board.api.exceptions.rentalExceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message){
        super(message);
    }
}
