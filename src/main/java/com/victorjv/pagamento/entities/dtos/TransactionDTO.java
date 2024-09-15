package com.victorjv.pagamento.entities.dtos;

public record TransactionDTO(Double amount, Long payerId, Long receiverId) {
}
