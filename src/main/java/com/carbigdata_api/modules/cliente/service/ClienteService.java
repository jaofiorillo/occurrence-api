package com.carbigdata_api.modules.cliente.service;

import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import com.carbigdata_api.modules.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public void salvarCliente(ClienteRequest request) {
        var cliente = Cliente.of(request);
        validarCpfExistente(cliente.getCpf());

        repository.save(cliente);
    }

    public void editarCliente(Integer id, ClienteRequest request) {
        var cliente = findUsuarioById(id);
        validarCpfExistenteParaEdicao(id, request.cpf());

        cliente.editarCliente(request);
        repository.save(cliente);
    }

    public List<ClienteResponse> buscarTodosClientes() {
        return repository.findAll().stream()
            .map(ClienteResponse::of)
            .toList();
    }

    private void validarCpfExistente(String cpf) {
        repository.findByCpf(cpf).ifPresent(cliente -> {
            throw new RuntimeException("Cpf existente");
        });
    }

    private void validarCpfExistenteParaEdicao(Integer id, String cpf) {
        repository.findByCpfAndIdNot(cpf, id).ifPresent(cliente -> {
            throw new RuntimeException("Cpf existente");
        });
    }

    private Cliente findUsuarioById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
