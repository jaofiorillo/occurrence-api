package com.carbigdata_api.modules.ocorrencia.service;

import com.carbigdata_api.modules.minio.enums.ETipoArquivo;
import com.carbigdata_api.modules.minio.service.MinioService;
import com.carbigdata_api.modules.ocorrencia.model.FotoOcorrencia;
import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.carbigdata_api.modules.ocorrencia.repository.FotoOcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FotoOcorrenciaService {

    private final MinioService minioService;
    private final FotoOcorrenciaRepository repository;

    public void salvarFotoOcorrencia(MultipartFile fotoOcorrencia, Ocorrencia ocorrencia) {
        try {
            var hashArquivo = minioService.gerarHash(fotoOcorrencia);
            var extensao = ETipoArquivo.buscarPorContentType(fotoOcorrencia.getContentType());
            var nomeArquivo = String.format("%s_%s%s", hashArquivo, ocorrencia.getId(), extensao.getExtensao());

            var arquivoSalvo = minioService.uploadArquivo(fotoOcorrencia, nomeArquivo);

            repository.save(FotoOcorrencia.of(arquivoSalvo.bucket(), hashArquivo, ocorrencia));
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar arquivo: " + fotoOcorrencia.getOriginalFilename(), ex);
        }
    }
}
