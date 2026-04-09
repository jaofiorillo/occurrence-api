package com.carbigdata_api.modules.endereco.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(String logradouro,
                              @NotBlank String bairro,
                              @NotBlank String cep,
                              @NotBlank String cidade,
                              @NotBlank String estado) {
}
