package com.victorjv.pagamento.services.exceptions;


public class TransacaoError extends RuntimeException{

    public TransacaoError(String msg){
        super(msg);
    }
}
