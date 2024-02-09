package com.board.api.exceptions.gameExceptions;

public class GameConflictNameException extends RuntimeException {
    
    public GameConflictNameException(String message){
        super(message);
    }
}
