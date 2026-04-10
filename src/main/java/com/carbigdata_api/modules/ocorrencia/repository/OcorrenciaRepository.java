package com.carbigdata_api.modules.ocorrencia.repository;

import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer>,
    QuerydslPredicateExecutor<Ocorrencia>, OcorrenciaRepositoryCustom {
}
