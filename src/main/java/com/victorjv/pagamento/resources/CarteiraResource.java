package com.victorjv.pagamento.resources;


import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.entities.dtos.CarteiraDTO;
import com.victorjv.pagamento.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraResource {

    @Autowired
    private CarteiraService service;


    @PostMapping
    public ResponseEntity<CarteiraDTO> insert(@RequestBody Carteira carteira){

        carteira = service.insert(carteira);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carteira.getId()).toUri();

        return ResponseEntity.created(uri).body(CarteiraService.toDTO(carteira));
    }

    @GetMapping
    public ResponseEntity<List<CarteiraDTO>> findAll(){

        List<CarteiraDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping
    public ResponseEntity<CarteiraDTO> update(@RequestBody Carteira carteira){

         return ResponseEntity.ok().body(CarteiraService.toDTO(service.update(carteira)));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CarteiraDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/document/{document}")
    public ResponseEntity<CarteiraDTO> findByDocument(@PathVariable String document){

        return ResponseEntity.ok().body(service.finbyCpf_cnpj(document));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
