create table matricula_aluno(

    id bigint not null auto_increment,
    aluno_id bigint,
    disciplina_id bigint,
    nota1 decimal(5,2),
    nota2 decimal(5,2),
    status varchar(20),

    primary key(id),
    constraint fk_matricula_aluno_aluno_id foreign key(aluno_id) references aluno(id),
    constraint fk_matricula_aluno_disciplina_id foreign key(disciplina_id) references disciplina(id)
);
