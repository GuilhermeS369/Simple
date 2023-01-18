package com.project.simple.services;

import com.project.simple.entities.Address;
import com.project.simple.entities.Client;
import com.project.simple.exceptions.DatabaseException;
import com.project.simple.exceptions.ResourceNotFoundException;
import com.project.simple.repositories.AddressRepository;
import com.project.simple.repositories.ClientRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;


    public Address findById(Long id) {
        Optional<Address> obj = addressRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    ;

    public List<Address> findByClientId(Long id) {
        return addressRepository.findByClientId(id);
    }

    public Address insert(Address obj, Long id) {

        Client cl = clientService.findById(id);
        cl.getAddressList().add(obj);
        clientService.insert(cl);
        return addressRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

    public Client updateMainAddress(Long idClient, Long idAdress) {
        try {
            Client cl = clientService.findById(idClient);
            Long idMainAddress;
            for (Address p : cl.getAddressList()) {
                if (p.getCharacter() == 'y') {
                    p.setCharacter('n');
                }
            }
            for (Address p : cl.getAddressList()) {
                {
                }
                if (p.getId() == idAdress) {
                    p.setCharacter('y');
                    idMainAddress = p.getId();
                }
            }
            clientService.insert(cl);
            return cl;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(idAdress);
        }
    }


}
