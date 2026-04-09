package com.carbigdata_api.modules.endereco.controller;

import com.carbigdata_api.modules.endereco.controller.contract.IEnderecoController;
import com.carbigdata_api.modules.endereco.dto.EnderecoRequest;
import com.carbigdata_api.modules.endereco.dto.EnderecoResponse;
import com.carbigdata_api.modules.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/endereco")
public class EnderecoController implements IEnderecoController {

    private final EnderecoService service;

    @Override
    public void salvarEndereco(@Valid @RequestBody EnderecoRequest request) {
        service.salvarEndereco(request);
    }

    @Override
    public List<EnderecoResponse> buscarEnderecos() {
        return service.buscarEnderecos();
    }
}
