package com.project.simple.entities.mapper;

import com.project.simple.entities.Client;
import com.project.simple.entities.DTO.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ClientDTO toClientDTO(Client client){
        return MODEL_MAPPER.map(client, ClientDTO.class);
    }

    public Client toClient(ClientDTO dto){
        return MODEL_MAPPER.map(dto, Client.class);
    }
}
