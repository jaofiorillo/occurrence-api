package com.carbigdata_api.modules.cliente.controller;

import com.carbigdata_api.modules.cliente.controller.contract.IClienteController;
import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import com.carbigdata_api.modules.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cliente")
public class ClienteController implements IClienteController {

    private final ClienteService clienteService;

    @Override
    public void salvarCliente(@Valid @RequestBody ClienteRequest request) {
        clienteService.salvarCliente(request);
    }

    @Override
    public void editarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteRequest request) {
        clienteService.editarCliente(id, request);
    }

    @Override
    public Page<ClienteResponse> buscarTodosClientes(@RequestParam PageRequest pageRequest) {
        return clienteService.buscarTodosClientes(pageRequest);
    }
}
