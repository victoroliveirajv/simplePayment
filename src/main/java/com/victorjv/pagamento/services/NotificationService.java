package com.victorjv.pagamento.services;

import com.victorjv.pagamento.entities.Carteira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(Carteira carteira, String message){

        String email = carteira.getEmail();

    }
}
