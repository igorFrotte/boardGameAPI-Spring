package com.board.api.exceptions.clientExceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message){
        super(message);
    }
}
