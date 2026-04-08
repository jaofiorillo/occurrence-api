package com.carbigdata_api.modules.ocorrencia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FOTO_OCORRENCIA")
public class FotoOcorrencia {

    @Id
    @SequenceGenerator(name = "SEQ_OCORRENCIA", sequenceName = "SEQ_OCORRENCIA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_OCORRENCIA", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "FK_OCORRENCIA", referencedColumnName = "ID",
        foreignKey = @ForeignKey(name = "FK_FOTO_OCORRENCIA"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Ocorrencia ocorrencia;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "DSC_PATH_BUCKET", nullable = false)
    private String dscPathBucket;

    @Column(name = "DSC_HASH", nullable = false)
    private String dscHash;

}
