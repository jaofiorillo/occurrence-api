package com.carbigdata_api.modules.ocorrencia.dto;

import com.carbigdata_api.modules.ocorrencia.predicate.OcorrenciaPredicate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record OcorrenciaFiltros(
    String nomeCliente,
    String cpfCliente,
    String cidade,
    @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial,
    @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal) {


    public OcorrenciaPredicate toPredicate() {
        return new OcorrenciaPredicate()
            .comCidade(cidade)
            .comCpfCliente(cpfCliente)
            .comNomeCliente(nomeCliente)
            .comDatas(dataInicial, dataFinal);
    }

}
