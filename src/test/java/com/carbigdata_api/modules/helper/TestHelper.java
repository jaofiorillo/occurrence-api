package com.carbigdata_api.modules.helper;

import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.endereco.model.Endereco;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaFiltros;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaRequest;
import com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia;
import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestHelper {

    public static ClienteRequest umClienteRequest() {
        return new ClienteRequest(
            "João Silva",
            "12345678901",
            "joao@email.com",
            LocalDate.of(2003, 9, 20),
            "12345678910");
    }

    public static OcorrenciaRequest umaOcorrenciaRequest() {
        return new OcorrenciaRequest(1, 1);
    }

    public static MultipartFile umaFotoValida() {
        return new MockMultipartFile("foto", "imagem.jpg", "image/jpeg", "conteudo".getBytes());
    }

    public static Ocorrencia umaOcorrencia() {
        return Ocorrencia.builder()
            .id(1)
            .statusOcorrencia(EStatusOcorrencia.ATIVA)
            .cliente(umCliente())
            .endereco(umEndereco())
            .build();
    }

    public static OcorrenciaFiltros umOcorrenciaFiltros() {
        return new OcorrenciaFiltros(
            "João Silva",
            "12345678901",
            "Rolândia",
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 4, 10)
        );
    }

    public static Cliente umCliente() {
        return Cliente.builder()
            .id(1)
            .nome("João Silva")
            .email("joao@email.com")
            .senha("senha_hash")
            .dataNascimento(LocalDate.of(2003, 9, 20))
            .cpf("12345678910")
            .dataCadastro(LocalDateTime.now())
            .build();
    }

    public static Endereco umEndereco() {
        return Endereco.builder()
            .id(1)
            .logradouro("Rua das Flores, 123")
            .cep("86600-000")
            .bairro("Centro")
            .cidade("Rolândia")
            .estado("PR")
            .build();
    }
}
