package com.carbigdata_api.modules.ocorrencia.repository;

import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import static com.carbigdata_api.modules.ocorrencia.model.QOcorrencia.ocorrencia;

@Repository
@RequiredArgsConstructor
public class OcorrenciaRepositoryImpl implements OcorrenciaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Page<Ocorrencia> findAllByPredicate(Pageable pageable, Predicate predicate) {
        var conteudo = new JPAQueryFactory(entityManager)
            .selectFrom(ocorrencia)
            .innerJoin(ocorrencia.cliente).fetchJoin()
            .innerJoin(ocorrencia.endereco).fetchJoin()
            .innerJoin(ocorrencia.fotoOcorrencias).fetchJoin()
            .where(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        var total = new JPAQueryFactory(entityManager)
            .select(ocorrencia.id.count())
            .from(ocorrencia)
            .where(predicate)
            .fetch().size();

        return new PageImpl<>(conteudo, pageable, total);

    }
}
