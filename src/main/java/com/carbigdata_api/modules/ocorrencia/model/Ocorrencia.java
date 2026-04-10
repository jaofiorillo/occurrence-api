package com.carbigdata_api.modules.ocorrencia.model;

import com.carbigdata_api.modules.cliente.model.Cliente;
import com.carbigdata_api.modules.endereco.model.Endereco;
import com.carbigdata_api.modules.ocorrencia.enums.EStatusOcorrencia;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ocorrencia")
public class Ocorrencia {

    @Id
    @SequenceGenerator(name = "seq_ocorrencia", sequenceName = "seq_ocorrencia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ocorrencia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_ocorrencia_cliente"))
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_ocorrencia_endereco"))
    private Endereco endereco;

    @Column(name = "data_ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_ocorrencia", nullable = false)
    private EStatusOcorrencia statusOcorrencia;

    public static Ocorrencia of(Cliente cliente, Endereco endereco, EStatusOcorrencia statusOcorrencia) {
        return Ocorrencia.builder()
            .cliente(cliente)
            .endereco(endereco)
            .statusOcorrencia(statusOcorrencia)
            .dataOcorrencia(LocalDateTime.now())
            .build();
    }
}
