package com.victorjv.pagamento.repositories;

import com.victorjv.pagamento.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByDocumento(String documento);
}
