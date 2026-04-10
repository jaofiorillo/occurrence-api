package com.carbigdata_api.modules.ocorrencia.service;

import com.carbigdata_api.config.exceptions.NotFoundException;
import com.carbigdata_api.config.exceptions.ValidationException;
import com.carbigdata_api.modules.cliente.service.ClienteService;
import com.carbigdata_api.modules.endereco.service.EnderecoService;
import com.carbigdata_api.modules.minio.enums.ETipoArquivoPermitido;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaFiltros;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaRequest;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaResponse;
import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.carbigdata_api.modules.ocorrencia.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.carbigdata_api.modules.common.utils.ListUtils.isEmpty;
import static com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia.ATIVA;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final ClienteService clienteService;
    private final OcorrenciaRepository repository;
    private final EnderecoService enderecoService;
    private final FotoOcorrenciaService fotoOcorrenciaService;

    @Transactional
    public void salvarOcorrencia(OcorrenciaRequest request, List<MultipartFile> fotosOcorrencia) {
        var cliente = clienteService.findClienteById(request.clienteId());
        var endereco = enderecoService.getEnderecoById(request.enderecoId());

        validarExtensaoArquivo(fotosOcorrencia);
        var ocorrenciaSalva = repository.save(Ocorrencia.of(cliente, endereco, ATIVA));

        if (!isEmpty(fotosOcorrencia)) {
            fotosOcorrencia.forEach(foto -> fotoOcorrenciaService.salvarFotoOcorrencia(foto, ocorrenciaSalva));
        }
    }

    private void validarExtensaoArquivo(List<MultipartFile> fotosOcorrencia) {
        if (!isEmpty(fotosOcorrencia)) {
            var arquivosNaoPermitidos = fotosOcorrencia.stream()
                .filter(file -> !ETipoArquivoPermitido.isPermitido(file.getContentType()))
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.joining(", "));

            if (isNotEmpty(arquivosNaoPermitidos)) {
                throw new ValidationException("Os seguintes arquivos não são permitidos: " + arquivosNaoPermitidos);
            }
        }
    }

    public Page<OcorrenciaResponse> listarOcorrencias(Pageable pageable, OcorrenciaFiltros filtros) {
        return repository.findAllByPredicate(pageable, filtros.toPredicate().build())
            .map(OcorrenciaResponse::of);
    }

    public void finalizarOcorrencia(Integer id) {
        var ocorrencia = findOcorrenciaById(id);
        ocorrencia.finalizarOcorrencia();

        repository.save(ocorrencia);
    }

    private Ocorrencia findOcorrenciaById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Ocorrência não encontrada"));
    }
}
