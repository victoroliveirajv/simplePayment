package com.victorjv.pagamento.services;

import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.entities.Transacao;
import com.victorjv.pagamento.entities.dtos.CarteiraDTO;
import com.victorjv.pagamento.entities.dtos.TransacaoDTO;
import com.victorjv.pagamento.repositories.TransacaoRepository;
import com.victorjv.pagamento.services.exceptions.TransacaoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private AutorizationTransation autorizationTransation;

    public void transferir(TransacaoDTO transacaoDTO){

        Carteira pagador = carteiraService.toCarteira(carteiraService.findById(transacaoDTO.pagadorId()));
        Carteira recebedor = carteiraService.toCarteira(carteiraService.findById(transacaoDTO.recebedorId()));

        validadeTransasaction(pagador,  recebedor, transacaoDTO.valor());
        try{
            autorizationTransation.authorizeTransaction();
        }
        catch (Exception e){
            throw new TransacaoError("Erro "+ e.getMessage());
        }

        Transacao transacao = new Transacao(null, pagador, recebedor, transacaoDTO.valor(), LocalDateTime.now());

        transacaoRepository.save(transacao);

        carteiraService.debitar(transacao.getValor(),transacao.getPagador());
        carteiraService.creditar(transacao.getValor(),transacao.getRecebedor());

    }

    public void validadeTransasaction(Carteira sender, Carteira reciver, Double amount){

        if (sender.getTipo() == 2){
            throw new TransacaoError("Usuarios lojistas não podem realizar transação.");
        }

        if (sender.getSaldo() < amount){
            throw new TransacaoError("Saldo insuficiente.");
        }

        if (sender.equals(reciver)){
            throw new TransacaoError("Não é permitido transferencias entre a mesma carteira.");
        }

    }

    public List<Transacao> findAll(){
        return transacaoRepository.findAll();
    }

}
