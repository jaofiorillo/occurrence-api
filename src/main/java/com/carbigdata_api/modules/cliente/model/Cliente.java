package com.carbigdata_api.modules.cliente.model;

import com.carbigdata_api.modules.cliente.dto.ClienteRequest;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_CLIENTE", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "CPF", nullable = false, length = 14)
    private String cpf;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    public static Cliente of(ClienteRequest request) {
        return Cliente.builder()
            .cpf(request.cpf())
            .nome(request.nome())
            .dataCadastro(LocalDateTime.now())
            .dataNascimento(request.dataNascimento())
            .build();
    }
}
