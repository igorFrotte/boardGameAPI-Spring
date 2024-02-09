package com.board.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.board.api.dtos.ClientDTO;
import com.board.api.models.ClientModel;
import com.board.api.repositories.ClientRepository;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @SuppressWarnings("null")
    public Optional<ClientModel> findById(Long id){
        return clientRepository.findById(id); //exception
    }

    public ClientModel save (ClientDTO dto){
        if(clientRepository.existsByCpf(dto.getCpf())){
            //exception
        }

        ClientModel client = new ClientModel(dto);
        return clientRepository.save(client);
    }

}