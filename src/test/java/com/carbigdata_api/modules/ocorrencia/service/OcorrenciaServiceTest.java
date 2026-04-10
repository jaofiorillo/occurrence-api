package com.carbigdata_api.modules.ocorrencia.service;

import com.carbigdata_api.config.exceptions.NotFoundException;
import com.carbigdata_api.config.exceptions.ValidationException;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.cliente.service.ClienteService;
import com.carbigdata_api.modules.endereco.model.Endereco;
import com.carbigdata_api.modules.endereco.service.EnderecoService;
import com.carbigdata_api.modules.ocorrencia.dto.OcorrenciaResponse;
import com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia;
import com.carbigdata_api.modules.ocorrencia.model.Ocorrencia;
import com.carbigdata_api.modules.ocorrencia.repository.OcorrenciaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static com.carbigdata_api.modules.helper.TestHelper.*;
import static com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia.ATIVA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OcorrenciaServiceTest {

    @InjectMocks
    private OcorrenciaService service;
    @Mock
    private ClienteService clienteService;
    @Mock
    private OcorrenciaRepository repository;
    @Mock
    private EnderecoService enderecoService;
    @Mock
    private FotoOcorrenciaService fotoOcorrenciaService;

    @Test
    @DisplayName("Deve salvar ocorrência com sucesso quando dados forem válidos")
    void salvarOcorrencia_deveSalvarOcorrencia_quandoDadosValidos() {
        var request = umaOcorrenciaRequest();
        var ocorrenciaSalva = new Ocorrencia();
        var foto = umaFotoValida();

        doReturn(umCliente())
            .when(clienteService).findClienteById(request.clienteId());
        doReturn(umEndereco())
            .when(enderecoService).getEnderecoById(request.enderecoId());
        doReturn(ocorrenciaSalva).when(repository).save(any(Ocorrencia.class));

        service.salvarOcorrencia(request, List.of(foto));

        verify(repository).save(any(Ocorrencia.class));
        verify(fotoOcorrenciaService).salvarFotoOcorrencia(eq(foto), eq(ocorrenciaSalva));
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao salvar quando extensão do arquivo não for permitida")
    void salvarOcorrencia_deveLancarRuntimeException_quandoExtensaoInvalida() {
        var request = umaOcorrenciaRequest();
        var fotoInvalida = new MockMultipartFile("foto", "arquivo.exe",
            "application/x-msdownload", new byte[1]);

        doReturn(new Cliente())
            .when(clienteService).findClienteById(request.clienteId());
        doReturn(new Endereco())
            .when(enderecoService).getEnderecoById(request.enderecoId());

        assertThatExceptionOfType(ValidationException.class)
            .isThrownBy(() -> service.salvarOcorrencia(request, List.of(fotoInvalida)))
            .withMessage("Os seguintes arquivos não são permitidos: arquivo.exe");

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve finalizar ocorrência com sucesso quando solicitado")
    void finalizarOcorrencia_deveFinalizarOcorrencia_quandoSolicitado() {
        var ocorrencia = umaOcorrencia();
        doReturn(Optional.of(ocorrencia))
            .when(repository).findById(1);

        service.finalizarOcorrencia(1);

        assertEquals(EStatusOcorrencia.FINALIZADA, ocorrencia.getStatusOcorrencia());
        verify(repository).save(ocorrencia);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar ocorrência inexistente")
    void findOcorrenciaById_deveLancarNotFoundException_quandoIdInexistente() {
        doReturn(Optional.empty()).when(repository).findById(99);

        assertThatExceptionOfType(NotFoundException.class)
            .isThrownBy(() -> service.finalizarOcorrencia(99))
            .withMessage("Ocorrência não encontrada");
    }

    @Test
    @DisplayName("Deve listar ocorrências paginadas quando solicitado")
    void listarOcorrencias_deveRetornarPagina_quandoSolicitado() {
        var pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(umaOcorrencia()));

        doReturn(page)
            .when(repository).findAllByPredicate(eq(pageable), any());

        assertThat(service.listarOcorrencias(pageable, umOcorrenciaFiltros()))
            .extracting(OcorrenciaResponse::id, OcorrenciaResponse::statusOcorrencia)
            .containsExactlyInAnyOrder(
                tuple(1, ATIVA));

        verify(repository).findAllByPredicate(eq(pageable), any());
    }
}
