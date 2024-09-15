package com.victorjv.pagamento.entities.dtos;

public record TransacaoDTO(Double valor, Long pagadorId, Long recebedorId) {
}
