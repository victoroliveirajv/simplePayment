package com.victorjv.pagamento;

import com.victorjv.pagamento.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class Application implements CommandLineRunner {

    @Autowired
    private CarteiraRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.findAll();

    }
}
