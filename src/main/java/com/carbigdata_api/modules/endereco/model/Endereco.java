package com.carbigdata_api.modules.endereco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ENDERECO", strategy = GenerationType.SEQUENCE)
    private Integer id;


}
