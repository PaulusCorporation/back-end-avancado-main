create table aluno(

    id bigint not null auto_increment,
    nome_completo varchar(100),
    email varchar(100),
    cpf varchar(14),

    primary key(id)
);
