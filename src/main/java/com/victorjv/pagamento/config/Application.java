package com.victorjv.pagamento.config;

import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.entities.Transacao;
import com.victorjv.pagamento.entities.enums.TIPO;
import com.victorjv.pagamento.repositories.CarteiraRepository;
import com.victorjv.pagamento.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Application implements CommandLineRunner {

    @Autowired
    private CarteiraRepository repository;

    @Autowired
    private TransacaoService transacaoService;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Carteira c1 = new Carteira(null, "José Rodrigues", "99999999-01","jose@gmail.com",
                "54823",546.0, TIPO.COMUM );
        Carteira c2 = new Carteira(null, "Bruna Souza", "9858545-01","bruna@gmail.com",
                "7879",1000.0, TIPO.COMUM );
        Carteira c3 = new Carteira(null, "Atacarejao", "878745-5","atacarejao@gmail.com",
                "458744",1200.0, TIPO.LOJISTA );

        repository.saveAll(Arrays.asList(c1, c2, c3));

        Transacao t1 = new Transacao(null, c2, c3, 10.0, LocalDateTime.now());
        Transacao t2 = new Transacao(null, c1, c3, 100.0, LocalDateTime.now());
        Transacao t3 = new Transacao(null, c1, c2, 24.0,LocalDateTime.now());
        Transacao t4 = new Transacao(null, c2, c3, 16.0,LocalDateTime.now());


        transacaoService.transferir(t1);
        transacaoService.transferir(t2);
        transacaoService.transferir(t3);
        transacaoService.transferir(t4);


    }
}
