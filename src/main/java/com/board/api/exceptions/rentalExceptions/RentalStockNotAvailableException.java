package com.board.api.exceptions.rentalExceptions;

public class RentalStockNotAvailableException extends RuntimeException {
    public RentalStockNotAvailableException (String message){
        super(message);
    }
}
