package com.carbigdata_api.modules.cliente.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
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
    private LocalDate dataCadastro;

}
