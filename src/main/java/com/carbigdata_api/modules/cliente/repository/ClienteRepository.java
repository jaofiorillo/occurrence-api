package com.carbigdata_api.modules.cliente.repository;

import com.carbigdata_api.modules.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>,
    QuerydslPredicateExecutor<Cliente> {

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpfAndIdNot(String cpf, Integer id);

    Page<Cliente> findAll(Pageable pageable);
}

