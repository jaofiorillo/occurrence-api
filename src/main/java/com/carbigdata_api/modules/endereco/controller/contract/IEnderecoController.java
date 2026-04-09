package com.carbigdata_api.modules.endereco.controller.contract;

import com.carbigdata_api.modules.endereco.annotations.EnderecoRequestParameter;
import com.carbigdata_api.modules.endereco.dto.EnderecoRequest;
import com.carbigdata_api.modules.endereco.dto.EnderecoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.carbigdata_api.modules.common.Constantes.CAMPO_OBG_N_INFORMADO;
import static com.carbigdata_api.modules.common.Constantes.DESCR_N_AUTORIZADO;

public interface IEnderecoController {

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO),
        @ApiResponse(responseCode = "400", description = CAMPO_OBG_N_INFORMADO)
    })
    @EnderecoRequestParameter
    @Operation(summary = "Endpoint para cadastro de endereço")
    void salvarEndereco(@RequestBody EnderecoRequest request);

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO)
    })
    @Operation(summary = "Endpoint para busca de endereços")
    List<EnderecoResponse> buscarEnderecos();
}
