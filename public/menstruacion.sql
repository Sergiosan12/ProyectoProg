create table menstruacion
(
    usuario       varchar(20) not null
        primary key
        references usuario,
    mediaciclo    integer     not null,
    mediasangrado integer     not null
);

alter table menstruacion
    owner to postgres;

