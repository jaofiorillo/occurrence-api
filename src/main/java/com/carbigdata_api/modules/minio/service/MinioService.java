package com.carbigdata_api.modules.minio.service;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public ObjectWriteResponse uploadArquivo(MultipartFile file, String nomeArquivo) {
        try {
            return minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(nomeArquivo)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao enviar arquivo", ex);
        }
    }

    public String gerarHash(MultipartFile file) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(file.getBytes()));
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao gerar hash", ex);
        }
    }
}
