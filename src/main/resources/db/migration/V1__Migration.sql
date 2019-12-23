CREATE TABLE setor (
    id SERIAL,
    nome VARCHAR(100),
    sigla VARCHAR(5),
    PRIMARY KEY (id)
);

INSERT INTO setor (nome, sigla) VALUES ('Administrativo', 'A');
INSERT INTO setor (nome, sigla) VALUES ('Tecnologia', 'T');
INSERT INTO setor (nome, sigla) VALUES ('Recursos Humanos', 'R');
INSERT INTO setor (nome, sigla) VALUES ('Help Desk', 'H');

CREATE TABLE gestor (
    id SERIAL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(100) NOT NULL,
    data_nascimento TIMESTAMP,
    id_setor INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (id_setor) REFERENCES setor(id)
);