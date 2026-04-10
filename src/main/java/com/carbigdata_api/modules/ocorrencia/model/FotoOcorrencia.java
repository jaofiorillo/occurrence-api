package com.carbigdata_api.modules.ocorrencia.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foto_ocorrencia")
public class FotoOcorrencia {

    @Id
    @SequenceGenerator(name = "seq_foto_ocorrencia", sequenceName = "seq_foto_ocorrencia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_foto_ocorrencia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_ocorrencia", foreignKey = @ForeignKey(name = "fk_foto_ocorrencia"), nullable = false)
    private Ocorrencia ocorrencia;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dsc_path_bucket", nullable = false)
    private String dscPathBucket;

    @Column(name = "dsc_hash", nullable = false)
    private String dscHash;

    public static FotoOcorrencia of(String dscPathBucket, String dscHash, Ocorrencia ocorrencia) {
        return FotoOcorrencia.builder()
            .dscHash(dscHash)
            .ocorrencia(ocorrencia)
            .dscPathBucket(dscPathBucket)
            .dataCadastro(LocalDateTime.now())
            .build();
    }

}
