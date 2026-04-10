package com.carbigdata_api.modules.cliente.service;

import com.carbigdata_api.config.exceptions.NotFoundException;
import com.carbigdata_api.config.exceptions.ValidationException;
import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.carbigdata_api.modules.helper.TestHelper.umClienteRequest;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;
    @Mock
    private ClienteRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve salvar cliente com sucesso quando dados forem válidos")
    void salvarCliente_deveSalvarCliente_quandoDadosValidos() {
        var request = umClienteRequest();

        doReturn(Optional.empty())
            .when(repository).findByCpf(any());
        doReturn(Optional.empty())
            .when(repository).findByEmail(any());
        doReturn("senha_hash")
            .when(passwordEncoder).encode(any());

        service.salvarCliente(request);

        verify(repository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve lançar ValidationException ao salvar quando CPF já existir")
    void salvarCliente_deveLancarValidationException_quandoCpfJaExistente() {
        var request = umClienteRequest();

        doReturn(Optional.of(new Cliente()))
            .when(repository).findByCpf(request.cpf());

        assertThrows(ValidationException.class, () -> service.salvarCliente(request));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve editar cliente com sucesso quando solicitado")
    void editarCliente_deveEditarCliente_quandoSolicitado() {
        var request = umClienteRequest();
        var cliente = new Cliente();

        doReturn(Optional.of(cliente))
            .when(repository).findById(1);
        doReturn(Optional.empty())
            .when(repository).findByCpfAndIdNot(request.cpf(), 1);
        doReturn(Optional.empty())
            .when(repository).findByEmailAndIdNot(request.email(), 1);

        service.editarCliente(1, request);

        verify(repository, times(1)).save(cliente);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar por ID inexistente")
    void findClienteById_deveLancarNotFoundException_quandoIdInexistente() {
        doReturn(Optional.empty())
            .when(repository).findById(99);

        assertThatExceptionOfType(NotFoundException.class)
            .isThrownBy(() -> service.findClienteById(99))
            .withMessage("Cliente não encontrado");
    }

    @Test
    @DisplayName("Deve lançar ValidationException ao editar quando CPF pertencer a outro usuário")
    void editarCliente_deveLancarValidationException_quandoCpfJaExistenteEmOutroId() {
        var request = umClienteRequest();

        doReturn(Optional.of(new Cliente()))
            .when(repository).findById(1);
        doReturn(Optional.of(new Cliente()))
            .when(repository).findByCpfAndIdNot(request.cpf(), 1);

        assertThatExceptionOfType(ValidationException.class)
            .isThrownBy(() -> service.editarCliente(1, request))
            .withMessage("Cpf existente");
    }
}
