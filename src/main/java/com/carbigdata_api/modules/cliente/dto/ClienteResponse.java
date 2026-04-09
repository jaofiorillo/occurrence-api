package com.carbigdata_api.modules.cliente.dto;

import com.carbigdata_api.modules.cliente.model.Cliente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ClienteResponse(Integer id,
                              String nome,
                              @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
                              String cpf) {

    public static ClienteResponse of(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf());
    }
}
