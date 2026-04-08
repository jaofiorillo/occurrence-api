package com.carbigdata_api.modules.cliente.controller;

import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public void salvarCliente(@Valid @RequestBody ClienteRequest request) {
        clienteService.salvarCliente(request);
    }
}
