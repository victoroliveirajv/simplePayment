package com.victorjv.pagamento.services;

import com.victorjv.pagamento.entities.Transacao;
import com.victorjv.pagamento.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CarteiraService carteiraService;

    public void transferir(Transacao transacao){

        if (transacao.getPagador().getTipo() == 2){
            throw new RuntimeException("Logistas não realizam pagamento");
        }
        if (transacao.getPagador().getSaldo() < transacao.getValor()){
            throw new RuntimeException("Saldo insuficiente.");
        }
        transacaoRepository.save(transacao);

        carteiraService.debitar(transacao.getValor(),transacao.getPagador());
        carteiraService.creditar(transacao.getValor(),transacao.getRecebedor());



    }
}
