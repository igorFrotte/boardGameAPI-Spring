package com.board.api.exceptions.gameExceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException (String message){
        super(message);
    }
}