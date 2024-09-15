package com.victorjv.pagamento.resources.exceptions;


import com.victorjv.pagamento.services.exceptions.TransactionError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(TransactionError.class)
    public ResponseEntity<StandardError> error(TransactionError e, HttpServletRequest request){

        String error = "Erro de tansasão";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError st = new StandardError(Instant.now(), status.value(), error, e.getMessage());

        return ResponseEntity.status(status).body(st);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> error(RuntimeException e, HttpServletRequest request){

        String error = "Não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError st = new StandardError(Instant.now(), status.value(), error, e.getMessage());

        return ResponseEntity.status(status).body(st);
    }

}
