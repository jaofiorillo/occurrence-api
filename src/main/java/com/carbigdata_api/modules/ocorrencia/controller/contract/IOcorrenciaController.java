package com.carbigdata_api.modules.ocorrencia.controller.contract;

import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaFiltros;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaRequest;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.carbigdata_api.modules.common.Constantes.CAMPO_OBG_N_INFORMADO;
import static com.carbigdata_api.modules.common.Constantes.DESCR_N_AUTORIZADO;

public interface IOcorrenciaController {

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO),
        @ApiResponse(responseCode = "400", description = CAMPO_OBG_N_INFORMADO)
    })
    @Operation(summary = "Endpoint para cadastrar Ocorrência")
    void salvarOcorrencia(@RequestPart OcorrenciaRequest request, @RequestPart List<MultipartFile> fotosOcorrencia);

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO)
    })
    @Operation(summary = "Endpoint para cadastrar Ocorrências")
    Page<OcorrenciaResponse> listarOcorrencias(OcorrenciaFiltros filtros,
                                               @PageableDefault(sort = "id") Pageable pageable);

    @PutMapping("{ocorrenciaId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO)
    })
    @Operation(summary = "Endpoint para finalizar Ocorrências")
    void finalizarOcorrencia(@PathVariable Integer ocorrenciaId);
}
