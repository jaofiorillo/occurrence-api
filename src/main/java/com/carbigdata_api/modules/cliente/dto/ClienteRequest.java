package com.carbigdata_api.modules.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ClienteRequest(
    @NotBlank @Size(max = 255) String email,
    @NotBlank @Size(max = 255) String senha,
    @NotBlank @Size(max = 255) String nome,
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
    @NotBlank @Size(max = 14) String cpf) {
}
