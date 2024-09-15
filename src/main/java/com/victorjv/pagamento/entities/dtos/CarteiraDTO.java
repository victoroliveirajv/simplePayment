package com.victorjv.pagamento.entities.dtos;

import com.victorjv.pagamento.entities.enums.TIPO;


public record CarteiraDTO(Long id, String nome, String documento, String email, Double saldo, TIPO tipo ){


}