package com.board.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.api.dtos.RentalDTO;
import com.board.api.exceptions.clientExceptions.ClientNotFoundException;
import com.board.api.repositories.ClientRepository;
import com.board.api.repositories.RentalRepository;
import com.board.api.services.RentalService;

@SpringBootTest
public class RentalUnitTests {

    @InjectMocks
	RentalService rentalService;

	@Mock
	private RentalRepository rentalRepository;

    @Mock
	private ClientRepository clientRepository;

    @Test
	void ClientNotExistWhenCreateRentalThrowsError(){

		RentalDTO rentalDTO = new RentalDTO(1L, 1L, 30);
		doReturn(Optional.empty()).when(clientRepository).findById(any());

		ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> rentalService.save(rentalDTO));
		
		assertNotNull(exception);
		assertEquals("Client not found.", exception.getMessage());
		verify(rentalRepository, times(0)).save(any());
		verify(clientRepository, times(1)).findById(any());

	}


}
