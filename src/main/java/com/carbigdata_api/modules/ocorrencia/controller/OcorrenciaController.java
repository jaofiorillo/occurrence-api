package com.carbigdata_api.modules.ocorrencia.controller;

import com.carbigdata_api.modules.ocorrencia.controller.contract.IOcorrenciaController;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaFiltros;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaRequest;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaResponse;
import com.carbigdata_api.modules.ocorrencia.service.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ocorrencia")
public class OcorrenciaController implements IOcorrenciaController {

    private final OcorrenciaService service;

    @Override
    public void salvarOcorrencia(@Valid @RequestPart OcorrenciaRequest request,
                                 @RequestPart List<MultipartFile> fotosOcorrencia) {
        service.salvarOcorrencia(request, fotosOcorrencia);
    }

    @Override
    public Page<OcorrenciaResponse> listarOcorrencias(@RequestParam OcorrenciaFiltros filtros,
                                                      @PageableDefault(sort = "id")Pageable pageable) {
        return service.listarOcorrencias(pageable, filtros);
    }
}
