package com.board.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.api.dtos.GameDTO;
import com.board.api.exceptions.gameExceptions.GameConflictNameException;
import com.board.api.repositories.GameRepository;
import com.board.api.services.GameService;

@SpringBootTest
class GameUnitTests {
	
	@InjectMocks
	GameService gameService;

	@Mock
	private GameRepository gameRepository;

	@Test
	void repeatedGameWhenCreateThrowsError(){

		GameDTO gameDTO = new GameDTO("name", "image", 2, 1000);
		doReturn(true).when(gameRepository).existsByName(any());

		GameConflictNameException exception = assertThrows(GameConflictNameException.class, () -> gameService.save(gameDTO));
		
		assertNotNull(exception);
		assertEquals("Game already exist.", exception.getMessage());
		verify(gameRepository, times(0)).save(any());
		verify(gameRepository, times(1)).existsByName(any());

	}

}

