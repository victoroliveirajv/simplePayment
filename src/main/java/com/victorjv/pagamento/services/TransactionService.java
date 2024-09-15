package com.victorjv.pagamento.services;

import com.victorjv.pagamento.entities.User;
import com.victorjv.pagamento.entities.Transaction;
import com.victorjv.pagamento.entities.dtos.TransactionDTO;
import com.victorjv.pagamento.repositories.TransactionRepository;
import com.victorjv.pagamento.services.exceptions.TransactionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AutorizationTransation autorizationTransation;

    public void transfer(TransactionDTO transactionDTO){

        User payer = userService.toUser(userService.findById(transactionDTO.payerId()));
        User receiver = userService.toUser(userService.findById(transactionDTO.receiverId()));

        validateTransaction(payer,  receiver, transactionDTO.amount());
        try{
            autorizationTransation.authorizeTransaction();
        }
        catch (Exception e){
            throw new TransactionError("Erro "+ e.getMessage());
        }

        Transaction transaction = new Transaction(null, payer, receiver, transactionDTO.amount(), LocalDateTime.now());

        transactionRepository.save(transaction);

        userService.debit(transaction.getAmount(), transaction.getPayer());
        userService.credit(transaction.getAmount(), transaction.getReceiver());

    }

    public void validateTransaction(User sender, User reciver, Double amount){

        if (sender.getType() == 2){
            throw new TransactionError("Usuarios lojistas não podem realizar transação.");
        }

        if (sender.getBalance() < amount){
            throw new TransactionError("Saldo insuficiente.");
        }

        if (sender.equals(reciver)){
            throw new TransactionError("Não é permitido transferencias entre a mesma carteira.");
        }

    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

}
