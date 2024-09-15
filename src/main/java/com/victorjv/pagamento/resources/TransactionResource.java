package com.victorjv.pagamento.resources;

import com.victorjv.pagamento.entities.Transaction;
import com.victorjv.pagamento.entities.dtos.TransactionDTO;
import com.victorjv.pagamento.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource {

    @Autowired
    private TransactionService service;


    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(){

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> newTransaction(@RequestBody TransactionDTO transaction){
        service.transfer(transaction);
        return ResponseEntity.noContent().build();
    }
}
