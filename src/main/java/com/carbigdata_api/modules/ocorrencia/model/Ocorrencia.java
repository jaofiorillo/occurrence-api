package com.carbigdata_api.modules.ocorrencia.model;

import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.endereco.model.Endereco;
import com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OCORRENCIA")
public class Ocorrencia {

    @Id
    @SequenceGenerator(name = "SEQ_OCORRENCIA", sequenceName = "SEQ_OCORRENCIA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_OCORRENCIA", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "FK_CLIENTE", referencedColumnName = "ID",
        foreignKey = @ForeignKey(name = "FK_CLIENTE_OCORRENCIA"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JoinColumn(name = "FK_ENDERECO", referencedColumnName = "ID",
        foreignKey = @ForeignKey(name = "FK_ENDERECO_OCORRENCIA"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Endereco endereco;

    @Column(name = "DATA_OCORRENCIA", nullable = false)
    private LocalDateTime dataOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_OCORRENCIA", nullable = false)
    private EStatusOcorrencia statusOcorrencia;

}
