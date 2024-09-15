package com.victorjv.pagamento.resources;


import com.victorjv.pagamento.entities.User;
import com.victorjv.pagamento.entities.dtos.UserDTO;
import com.victorjv.pagamento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;


    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody User user){

        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(UserService.toDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        List<UserDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/document/{document}")
    public ResponseEntity<UserDTO> findByDocument(@PathVariable String document){

        return ResponseEntity.ok().body(service.finByDocument(document));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody User user){

        return ResponseEntity.ok().body(UserService.toDTO(service.update(user)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
