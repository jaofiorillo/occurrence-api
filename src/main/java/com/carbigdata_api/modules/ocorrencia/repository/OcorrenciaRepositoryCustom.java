package com.carbigdata_api.modules.ocorrencia.repository;

import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OcorrenciaRepositoryCustom {

    Page<Ocorrencia> findAllByPredicate(Pageable pageable, Predicate predicate);
}
