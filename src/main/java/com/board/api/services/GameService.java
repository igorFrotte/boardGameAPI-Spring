package com.board.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.api.dtos.GameDTO;
import com.board.api.exceptions.gameExceptions.GameConflictNameException;
import com.board.api.models.GameModel;
import com.board.api.repositories.GameRepository;

@Service
public class GameService {
    final GameRepository gameRepository;

    GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll(){
        return gameRepository.findAll();
    }

    public GameModel save (GameDTO dto){
        if(gameRepository.existsByName(dto.getName())){
            throw new GameConflictNameException("Game already exist.");
        }

        GameModel game = new GameModel(dto);
        return gameRepository.save(game);
    }
}