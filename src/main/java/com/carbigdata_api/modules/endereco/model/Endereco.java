package com.carbigdata_api.modules.endereco.model;

import com.carbigdata_api.modules.endereco.dto.EnderecoRequest;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco {

    @Id
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    private Integer id;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    public static Endereco of(EnderecoRequest request) {
        return Endereco.builder()
            .cep(request.cep())
            .bairro(request.bairro())
            .cidade(request.cidade())
            .estado(request.estado())
            .logradouro(request.logradouro())
            .build();
    }

}
