package com.board.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.api.dtos.ClientDTO;
import com.board.api.models.ClientModel;
import com.board.api.services.ClientService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping
public class ClientController {
    
    final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ClientModel> getClientsById(@PathVariable Long id) {
        ClientModel client = clientService.findById(id); //change
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
    

    @PostMapping("/customers")
    public ResponseEntity<ClientModel> createNewClient(@RequestBody @Valid ClientDTO body) {
        ClientModel client = clientService.save(body);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }
    
}