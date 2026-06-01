create table disciplina(

    id bigint not null auto_increment,
    nome varchar(100),
    descricao varchar(255),
    carga_horaria int,
    professor_id bigint,

    primary key(id),
    constraint fk_disciplina_professor_id foreign key(professor_id) references professor(id)
);
