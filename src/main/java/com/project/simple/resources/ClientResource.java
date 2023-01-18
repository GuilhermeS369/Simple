package com.project.simple.resources;


import com.project.simple.entities.Client;
import com.project.simple.entities.DTO.ClientDTO;
import com.project.simple.entities.mapper.ClientMapper;
import com.project.simple.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping(value = "/client")
@Api(tags = "Client Controller")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @ApiOperation("Busque um cliente")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client clt = clientService.findById(id);
        return ResponseEntity.ok().body(clt);
    }

    @ApiOperation("Insira um novo cliente")
    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client obj) {
        obj = clientService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);

    }

    @ApiOperation("Exclua um cliente")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @ApiOperation("Altere um cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientDTO obj) {
        Client obj2 = clientService.update(id, clientMapper.toClient(obj));
        return ResponseEntity.ok().body(obj2);
    }


}
