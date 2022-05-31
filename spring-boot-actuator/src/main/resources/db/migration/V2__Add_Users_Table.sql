create sequence user_id_seq start with 1 increment by 50;

create table users
(
    id    bigint DEFAULT nextval('user_id_seq') not null,
    email varchar(255)                              not null,
    name  varchar(255)                              not null,
    primary key (id)
);
