package com.carbigdata_api.modules.endereco.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@Parameter(
    in = ParameterIn.QUERY,
    name = "logradouro",
    description = "Logradouro",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "bairro",
    description = "Bairro",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "cep",
    description = "Cep",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "cidade",
    description = "Cidade",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "estado",
    description = "Estado",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
public @interface EnderecoRequestParameter {
}
