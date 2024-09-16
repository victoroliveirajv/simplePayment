package com.victorjv.pagamento.services;

import com.victorjv.pagamento.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// No yet implemented (Ainda não implementado)

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message){

        String email = user.getEmail();

    }
}
