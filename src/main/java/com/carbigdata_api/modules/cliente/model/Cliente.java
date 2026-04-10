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
@Table(name = "cliente")
public class Cliente {

    @Id
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "CPF", nullable = false, length = 12)
    private String cpf;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    public static Cliente of(ClienteRequest request) {
        return Cliente.builder()
            .cpf(request.cpf())
            .nome(request.nome())
            .email(request.email())
            .senha(request.senha())
            .dataCadastro(LocalDateTime.now())
            .dataNascimento(request.dataNascimento())
            .build();
    }

    public void editarCliente(ClienteRequest request) {
        this.cpf = request.cpf();
        this.nome = request.nome();
        this.dataNascimento = request.dataNascimento();
    }
}
