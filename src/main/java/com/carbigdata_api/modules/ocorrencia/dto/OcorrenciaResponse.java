package com.carbigdata_api.modules.ocorrencia.dto;

import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import com.carbigdata_api.modules.endereco.dto.EnderecoResponse;
import com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia;
import com.carbigdata_api.modules.ocorrencia.model.FotoOcorrencia;
import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;


public record OcorrenciaResponse(Integer id,
                                 @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataOcorrencia,
                                 EStatusOcorrencia statusOcorrencia,
                                 List<String> linksFotoOcorrencia,
                                 ClienteResponse cliente,
                                 EnderecoResponse enderecoResponse) {

    public static OcorrenciaResponse of(Ocorrencia ocorrencia) {
        return new OcorrenciaResponse(
            ocorrencia.getId(),
            ocorrencia.getDataOcorrencia(),
            ocorrencia.getStatusOcorrencia(),
            preencherLinksFotoOcorrrencia(ocorrencia.getFotoOcorrencias()),
            ClienteResponse.of(ocorrencia.getCliente()),
            EnderecoResponse.of(ocorrencia.getEndereco()));
    }

    private static List<String> preencherLinksFotoOcorrrencia(List<FotoOcorrencia> fotoOcorrencias) {
        return isNotEmpty(fotoOcorrencias)
            ? fotoOcorrencias.stream()
            .map(FotoOcorrencia::getDscHash).toList() : List.of();
    }
}
