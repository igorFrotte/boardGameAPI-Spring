package com.board.api.exceptions.rentalExceptions;

public class RentalAlreadyCompletedException extends RuntimeException{
    public RentalAlreadyCompletedException (String message){
        super(message);
    }
}
