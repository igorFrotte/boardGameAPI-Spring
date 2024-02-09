package com.board.api.services;

import org.springframework.stereotype.Service;

import com.board.api.dtos.ClientDTO;
import com.board.api.exceptions.clientExceptions.ClientConflictCPFException;
import com.board.api.exceptions.clientExceptions.ClientNotFoundException;
import com.board.api.models.ClientModel;
import com.board.api.repositories.ClientRepository;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientModel findById(Long id){
        return clientRepository.findById(id).orElseThrow(
            () -> new ClientNotFoundException("Client id is not found."));
    }

    public ClientModel save (ClientDTO dto){
        if(clientRepository.existsByCpf(dto.getCpf())){
            throw new ClientConflictCPFException("CPF already exist.");
        }

        ClientModel client = new ClientModel(dto);
        return clientRepository.save(client);
    }

}