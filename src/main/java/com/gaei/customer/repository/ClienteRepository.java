package com.gaei.customer.repository;

import com.gaei.customer.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByTipoDocumentoAndNumeroDocumento(
            String tipoDocumento, String numeroDocumento
    );

    boolean existsByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);

}
