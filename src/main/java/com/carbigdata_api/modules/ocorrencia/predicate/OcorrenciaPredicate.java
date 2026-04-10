package com.carbigdata_api.modules.ocorrencia.predicate;

import com.carbigdata_api.modules.common.predicate.PredicateBase;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.carbigdata_api.modules.ocorrencia.model.QOcorrencia.ocorrencia;
import static io.micrometer.common.util.StringUtils.isNotBlank;

@Builder
@AllArgsConstructor
public class OcorrenciaPredicate extends PredicateBase {

    public OcorrenciaPredicate comDatas(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial != null && dataFinal != null) {
            builder.and(ocorrencia.dataOcorrencia.after(dataInicial.atTime(LocalTime.MIN))
                .and(ocorrencia.dataOcorrencia.before(dataFinal.atTime(LocalTime.MAX))));
        }

        return this;
    }

    public OcorrenciaPredicate comCidade(String cidade) {
        if (isNotBlank(cidade)) {
            builder.and(ocorrencia.endereco.cidade.eq(cidade));
        }

        return this;
    }

    public OcorrenciaPredicate comNomeCliente(String nome) {
        if (isNotBlank(nome)) {
            builder.and(ocorrencia.cliente.nome.eq(nome));
        }

        return this;
    }

    public OcorrenciaPredicate comCpfCliente(String cpf) {
        if (isNotBlank(cpf)) {
            builder.and(ocorrencia.cliente.cpf.eq(cpf));
        }

        return this;
    }
}
