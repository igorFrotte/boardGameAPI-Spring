package com.board.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.board.api.dtos.RentalDTO;
import com.board.api.models.ClientModel;
import com.board.api.models.GameModel;
import com.board.api.repositories.ClientRepository;
import com.board.api.repositories.GameRepository;
import com.board.api.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalIntegrationTests {
    
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDb(){
        rentalRepository.deleteAll();
        clientRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
	void createValidRental(){
        
        GameModel GameModel = new GameModel(null,"name", "image", 30, 1000);
        GameModel createdGame = gameRepository.save(GameModel);

        ClientModel clientModel = new ClientModel(null, "name", "11122233344");
        ClientModel createdClient = clientRepository.save(clientModel);

        RentalDTO rentalDTO = new RentalDTO(createdClient.getId(), createdGame.getId(), 3);
        HttpEntity<RentalDTO> body = new HttpEntity<>(rentalDTO);

        ResponseEntity<GameModel> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, GameModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, rentalRepository.count());

    }

}
