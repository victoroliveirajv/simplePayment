package com.victorjv.pagamento.config;

import com.victorjv.pagamento.entities.User;
import com.victorjv.pagamento.entities.enums.TYPE;
import com.victorjv.pagamento.repositories.UserRepository;
import com.victorjv.pagamento.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        User u1 = new User(null, "José Rodrigues", "99999999-01","jose@gmail.com",
                "54823",546.0, TYPE.COMMON );
        User u2 = new User(null, "Bruna Souza", "9858545-01","bruna@gmail.com",
                "7879",1000.0, TYPE.COMMON );
        User u3 = new User(null, "Atacarejao", "878745-5","atacarejao@gmail.com",
                "458744",1200.0, TYPE.LOGIST);

        repository.saveAll(Arrays.asList(u1, u2, u3));

       /* TransacaoDTO t1 = new TransacaoDTO(5.0, c2.getId(), c3.getId());
        TransacaoDTO t2 = new TransacaoDTO(10.0, c1.getId(), c3.getId());
        TransacaoDTO t3 = new TransacaoDTO(2.0, c1.getId(), c2.getId());
        TransacaoDTO t4 = new TransacaoDTO(9.0, c2.getId(), c3.getId());
        transacaoService.transferir(t1);
        transacaoService.transferir(t2);
        transacaoService.transferir(t3);
        transacaoService.transferir(t4);
        */

    }
}
