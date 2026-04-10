package com.carbigdata_api.modules.minio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ETipoArquivoPermitido {

    PNG(".png", "image/png"),
    JPG(".jpg", "image/jpeg"),
    JPEG(".jpeg", "image/jpeg");

    private final String extensao;
    private final String contentType;

    public static boolean isPermitido(String extensaoArquivo) {
        return Arrays.stream(ETipoArquivoPermitido.values())
            .anyMatch(tipoArquivoPermitido -> Objects.equals(tipoArquivoPermitido.contentType, extensaoArquivo));
    }
}
