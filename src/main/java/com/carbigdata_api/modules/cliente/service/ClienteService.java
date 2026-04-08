package com.carbigdata_api.modules.cliente.service;

import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public void salvarCliente(ClienteRequest request) {
        var cliente = Cliente.of(request);
        validarCpfExistente(cliente.getCpf());

        repository.save(cliente);
    }

    public void validarCpfExistente(String cpf) {
        repository.findByCpf(cpf).ifPresent(usuario -> {
            throw new RuntimeException("Cpf existente");
        });
    }
}
