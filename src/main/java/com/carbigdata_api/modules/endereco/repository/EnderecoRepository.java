package com.carbigdata_api.modules.endereco.repository;

import com.carbigdata_api.modules.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
