package com.victorjv.pagamento.services;

import com.victorjv.pagamento.services.exceptions.AutorizationError;
import com.victorjv.pagamento.services.exceptions.TransactionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AutorizationTransation {

    @Autowired
    private RestTemplate restTemplate;

    public void authorizeTransaction(){
        try{
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
        }
        catch (Exception e){
            throw new AutorizationError("Transação não autorizada.");
        }
    }
}
