package com.carbigdata_api.modules.ocorrencia.controller.contract;

import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.carbigdata_api.modules.common.Constantes.CAMPO_OBG_N_INFORMADO;
import static com.carbigdata_api.modules.common.Constantes.DESCR_N_AUTORIZADO;

public interface IOcorrenciaController {

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = DESCR_N_AUTORIZADO),
        @ApiResponse(responseCode = "400", description = CAMPO_OBG_N_INFORMADO)
    })
    @Operation(summary = "Endpoint para cadastrar ocorrencias")
    void salvarOcorrencia(OcorrenciaRequest request, List<MultipartFile> fotosOcorrencia);
}
