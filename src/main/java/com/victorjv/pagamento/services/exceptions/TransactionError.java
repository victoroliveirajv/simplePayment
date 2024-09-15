package com.victorjv.pagamento.services.exceptions;


public class TransactionError extends RuntimeException{

    public TransactionError(String msg){
        super(msg);
    }
}
