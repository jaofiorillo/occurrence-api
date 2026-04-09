package com.carbigdata_api.modules.endereco.dto;

import com.carbigdata_api.modules.endereco.model.Endereco;

public record EnderecoResponse(Integer id,
                               String logradouro,
                               String bairro,
                               String cep,
                               String cidade,
                               String estado) {

    public static EnderecoResponse of(Endereco endereco) {
        return new EnderecoResponse(
            endereco.getId(),
            endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getCep(),
            endereco.getCidade(),
            endereco.getEstado());
    }
}
