package com.victorjv.pagamento.services;


import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    public void debitar(Double valor, Carteira carteira){
        carteira.debitar(valor);
        carteiraRepository.save(carteira);
    }

    public void creditar(Double valor, Carteira carteira){
        carteira.creditar(valor);
        carteiraRepository.save(carteira);
    }

}
