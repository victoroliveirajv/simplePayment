package com.victorjv.pagamento.entities.dtos;


import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.entities.enums.TIPO;
import jakarta.persistence.Column;

public record CarteiraDTO(Long id, String nome, String documento, String email, Double saldo, TIPO tipo ){


}