package com.carbigdata_api.modules.cliente.controller;

import com.carbigdata_api.modules.cliente.controller.contract.IClienteController;
import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import com.carbigdata_api.modules.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

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
    public List<ClienteResponse> buscarTodosClientes() {
        return clienteService.buscarTodosClientes();
    }
}
