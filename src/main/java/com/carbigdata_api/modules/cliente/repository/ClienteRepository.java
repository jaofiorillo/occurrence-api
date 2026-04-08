package com.carbigdata_api.modules.cliente.repository;

import com.carbigdata_api.modules.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCpf(String cpf);
}

