package com.victorjv.pagamento.entities.dtos;

import com.victorjv.pagamento.entities.enums.Type;


public record UserDTO(Long id, String name, String document, String email, Double balance, Type type){
}