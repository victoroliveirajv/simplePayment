package com.victorjv.pagamento.repositories;

import com.victorjv.pagamento.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long > {
}
