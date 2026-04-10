package com.carbigdata_api.modules.cliente.service;

import com.carbigdata_api.config.exceptions.NotFoundException;
import com.carbigdata_api.config.exceptions.ValidationException;
import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvarCliente(ClienteRequest request) {
        var cliente = Cliente.of(request);
        validarCpfExistente(cliente.getCpf());
        validarEmailExistente(request.email());

        cliente.setSenha(passwordEncoder.encode(request.senha()));
        repository.save(cliente);
    }

    public void editarCliente(Integer id, ClienteRequest request) {
        var cliente = findClienteById(id);
        validarCpfExistenteParaEdicao(id, request.cpf());
        validarEmailExistenteParaEdicao(id, request.email());

        cliente.editarCliente(request);
        repository.save(cliente);
    }

    public Page<ClienteResponse> buscarTodosClientes(Pageable pageable) {
        return repository.findAll(pageable)
            .map(ClienteResponse::of);
    }

    private void validarCpfExistente(String cpf) {
        repository.findByCpf(cpf).ifPresent(cliente -> {
            throw new ValidationException("Cpf existente");
        });
    }

    private void validarEmailExistente(String email) {
        repository.findByEmail(email).ifPresent(cliente -> {
            throw new ValidationException("Email existente");
        });
    }

    private void validarCpfExistenteParaEdicao(Integer id, String cpf) {
        repository.findByCpfAndIdNot(cpf, id).ifPresent(cliente -> {
            throw new ValidationException("Cpf existente");
        });
    }

    private void validarEmailExistenteParaEdicao(Integer id, String email) {
        repository.findByEmailAndIdNot(email, id).ifPresent(cliente -> {
            throw new ValidationException("Email existente");
        });
    }

    public Cliente findClienteById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
