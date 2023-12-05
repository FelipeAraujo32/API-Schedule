CREATE TABLE agenda(
    id serial PRIMARY KEY,
    descricao varchar(255),
    entrada DATETIME,
    agendamento DATETIME,
    pacientes_id integer,
    FOREIGN KEY(pacientes_id) REFERENCES pacientes(id)
    );