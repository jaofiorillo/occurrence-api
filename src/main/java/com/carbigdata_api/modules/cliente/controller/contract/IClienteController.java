package com.carbigdata_api.modules.cliente.controller.contract;

import com.carbigdata_api.modules.cliente.annotations.ClienteRequestParameter;
import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.dto.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.carbigdata_api.modules.common.Constantes.CAMPO_OBG_N_INFORMADO;
import static com.carbigdata_api.modules.common.Constantes.DESCR_N_AUTORIZADO;

public interface IClienteController {

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO),
        @ApiResponse(responseCode = "400", description = CAMPO_OBG_N_INFORMADO)
    })
    @ClienteRequestParameter
    @Operation(summary = "Endpoint para cadastro de cliente")
    void salvarCliente(@RequestBody ClienteRequest request);

    @PutMapping("{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO),
        @ApiResponse(responseCode = "400", description = CAMPO_OBG_N_INFORMADO)
    })
    @ClienteRequestParameter
    @Operation(summary = "Endpoint para edição de cliente")
    void editarCliente(@PathVariable Integer id, @RequestBody ClienteRequest request);

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO)
    })
    @Operation(summary = "Endpoint para busca de clientes")
    Page<ClienteResponse> buscarTodosClientes(@RequestParam PageRequest pageRequest);
}
