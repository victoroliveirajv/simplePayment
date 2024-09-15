package com.victorjv.pagamento.resources;

import com.victorjv.pagamento.entities.Transacao;
import com.victorjv.pagamento.entities.dtos.TransacaoDTO;
import com.victorjv.pagamento.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacoes")
public class TransationResource {

    @Autowired
    private TransacaoService service;


    @GetMapping
    public ResponseEntity<List<Transacao>> findAll(){

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> newTransaction(@RequestBody TransacaoDTO transacao){
        service.transferir(transacao);
        return ResponseEntity.noContent().build();
    }
}
