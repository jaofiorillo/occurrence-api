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
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ENDERECO", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "LOGRADOURO", nullable = false)
    private String logradouro;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "ESTADO", nullable = false)
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
