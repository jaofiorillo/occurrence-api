package com.carbigdata_api.modules.endereco.service;

import com.carbigdata_api.modules.endereco.dto.EnderecoRequest;
import com.carbigdata_api.modules.endereco.dto.EnderecoResponse;
import com.carbigdata_api.modules.endereco.model.Endereco;
import com.carbigdata_api.modules.endereco.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public void salvarEndereco(EnderecoRequest request) {
        try {
            repository.save(Endereco.of(request));
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao cadastrar endereço", ex);
        }
    }

    public List<EnderecoResponse> buscarEnderecos() {
        return repository.findAll().stream()
            .map(EnderecoResponse::of)
            .toList();
    }
 }
