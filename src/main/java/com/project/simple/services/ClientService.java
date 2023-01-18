package com.project.simple.services;

import com.project.simple.entities.Address;
import com.project.simple.entities.Client;
import com.project.simple.exceptions.DatabaseException;
import com.project.simple.exceptions.ResourceNotFoundException;
import com.project.simple.repositories.AddressRepository;
import com.project.simple.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;

    public Client findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    ;


    public Client insert(Client obj) {
        obj.getAddressList().forEach(Address -> Address.setClient(obj));
        clientRepository.save(obj);
        addressRepository.saveAll(obj.getAddressList());
        return clientRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            Client cli = findById(id);
            for (Address a : cli.getAddressList()) {
                addressRepository.delete(a);
            }
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

    public Client update(Long id, Client obj) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            updateData(entity, obj);
            return clientRepository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(Client entity, Client obj) {
        entity.setName(obj.getName());
        entity.setDate(obj.getDate());
    }

}
