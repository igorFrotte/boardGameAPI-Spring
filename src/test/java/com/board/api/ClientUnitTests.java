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

import com.board.api.dtos.ClientDTO;
import com.board.api.exceptions.clientExceptions.ClientConflictCPFException;
import com.board.api.repositories.ClientRepository;
import com.board.api.services.ClientService;

@SpringBootTest
public class ClientUnitTests {
    
    @InjectMocks
    ClientService clientService;

    @Mock
    private ClientRepository clientRepository;


    @Test
    void repeatedCPFWhenCreateThrowsError(){
        ClientDTO clientDTO = new ClientDTO("name", "11122233344");
        doReturn(true).when(clientRepository).existsByCpf(any());

        ClientConflictCPFException exception = assertThrows(ClientConflictCPFException.class, () -> clientService.save(clientDTO));

        assertNotNull(exception);
		assertEquals("CPF already exist.", exception.getMessage());
		verify(clientRepository, times(0)).save(any());
		verify(clientRepository, times(1)).existsByCpf(any());
    }

}