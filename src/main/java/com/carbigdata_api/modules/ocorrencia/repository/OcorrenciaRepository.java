package com.carbigdata_api.modules.ocorrencia.repository;

import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {
}
