package com.project.simple.resources;

import com.project.simple.entities.Address;
import com.project.simple.entities.Client;
import com.project.simple.services.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/address")
public class AddressResource {
    @Autowired
    private AddressService addressService;


    @ApiOperation("Busque endereços de um cliente")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Address>> findByClientId(@PathVariable Long id) {
        List<Address> list = addressService.findByClientId(id);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("Insira um novo endereço")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Address> insert(@RequestBody Address obj, @PathVariable Long id) {

        obj = addressService.insert(obj, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);

    }

    @ApiOperation("Exclua um endereço")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @ApiOperation("Altere o endereço principal")
    @PutMapping(value = "/{idClient}/{idAddress}")
    public ResponseEntity<Client> updateMainAddress(@PathVariable Long idClient, @PathVariable Long idAddress) {
        Client obj = addressService.updateMainAddress(idClient, idAddress);
        return ResponseEntity.ok().body(obj);
    }


}
