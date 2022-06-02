create sequence user_id_seq start with 1 increment by 50;
create sequence bookmark_id_seq start with 1 increment by 50;

create table users
(
    id       bigint DEFAULT nextval('user_id_seq') not null,
    email    varchar(255)                          not null,
    password varchar(255)                          not null,
    role     varchar(20)                           not null,
    name     varchar(255)                          not null,
    primary key (id)
);

create table bookmarks
(
    id    bigint DEFAULT nextval('bookmark_id_seq') not null,
    title varchar(255)                              not null,
    url   varchar(255)                              not null,
    primary key (id)
);
