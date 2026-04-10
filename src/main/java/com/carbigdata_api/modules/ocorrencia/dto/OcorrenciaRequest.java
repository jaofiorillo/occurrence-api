package com.carbigdata_api.modules.ocorrencia.dto;

import jakarta.validation.constraints.NotNull;

public record OcorrenciaRequest(@NotNull Integer clienteId,
                                @NotNull Integer enderecoId) {
}
