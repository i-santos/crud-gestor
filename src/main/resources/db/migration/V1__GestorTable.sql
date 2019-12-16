CREATE TABLE setor (
    id SERIAL,
    nome VARCHAR(100),
    sigla VARCHAR(5),
    PRIMARY KEY (id)
);

CREATE TABLE gestor (
    id SERIAL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(100) NOT NULL,
    data_nascimento DATE,
    id_setor INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (id_setor) REFERENCES setor(id)
);