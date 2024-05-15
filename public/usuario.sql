create table usuario
(
    id          serial
        primary key,
    nombre      varchar(20) not null,
    usuario     varchar(20) not null,
    contrasenha varchar(20) not null,
    email       varchar(50) not null,
    edad        integer     not null
);

alter table usuario
    owner to postgres;

create table usuario
(
    usuario     varchar(20) not null
        primary key,
    nombre      varchar(20) not null,
    contrasenha varchar(20) not null,
    email       varchar(50) not null,
    edad        integer     not null
);

alter table usuario
    owner to postgres;

