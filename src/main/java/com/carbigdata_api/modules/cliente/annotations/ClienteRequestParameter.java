package com.carbigdata_api.modules.cliente.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@Parameter(
    in = ParameterIn.QUERY,
    name = "nome",
    description = "Nome",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "cpf",
    description = "Cpf",
    required = true,
    schema = @Schema(type = "string", defaultValue = "null", example = "12345678910"))
@Parameter(
    in = ParameterIn.QUERY,
    name = "dataNascimento",
    description = "Data de nascimento",
    schema = @Schema(type = "string", format = "date", example = "20-09-2000"))
public @interface ClienteRequestParameter {
}
