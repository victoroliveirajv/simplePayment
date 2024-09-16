package com.victorjv.pagamento.services.exceptions;

public class AutorizationError extends RuntimeException{
    public AutorizationError(String msg){
        super(msg);
    }
}
