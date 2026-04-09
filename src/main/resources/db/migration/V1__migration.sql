-- TABELA CLIENTE
CREATE SEQUENCE seq_cliente START WITH 1 INCREMENT BY 1;
CREATE TABLE cliente
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('seq_cliente'),
    nome            VARCHAR(255),
    data_nascimento DATE        NOT NULL,
    cpf             VARCHAR(14) NOT NULL UNIQUE,
    data_cadastro   TIMESTAMP   NOT NULL
);

-- TABELA ENDERECO
CREATE SEQUENCE seq_endereco START WITH 1 INCREMENT BY 1;
CREATE TABLE endereco
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('seq_endereco'),
    logradouro VARCHAR(255),
    cep        VARCHAR(10)  NOT NULL,
    bairro     VARCHAR(255) NOT NULL,
    cidade     VARCHAR(255) NOT NULL,
    estado     VARCHAR(255) NOT NULL
);

-- TABELA OCORRENCIA
CREATE SEQUENCE seq_ocorrencia START WITH 1 INCREMENT BY 1;
CREATE TABLE ocorrencia
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('seq_ocorrencia'),
    fk_cliente        INTEGER     NOT NULL,
    fk_endereco       INTEGER     NOT NULL,
    data_ocorrencia   TIMESTAMP   NOT NULL,
    status_ocorrencia VARCHAR(15) NOT NULL,

    CONSTRAINT fk_ocorrencia_cliente
        FOREIGN KEY (fk_cliente)
            REFERENCES cliente (id),

    CONSTRAINT fk_ocorrencia_endereco
        FOREIGN KEY (fk_endereco)
            REFERENCES endereco (id)
);

-- TABELA FOTO OCORRENCIA
CREATE SEQUENCE seq_foto_ocorrencia;
CREATE TABLE foto_ocorrencia
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('seq_foto_ocorrencia'),
    fk_ocorrencia   INTEGER      NOT NULL,
    data_cadastro   DATE         NOT NULL,
    dsc_path_bucket VARCHAR(255) NOT NULL,
    dsc_hash        VARCHAR(255) NOT NULL,

    CONSTRAINT fk_foto_ocorrencia
        FOREIGN KEY (fk_ocorrencia)
            REFERENCES ocorrencia (id)
);