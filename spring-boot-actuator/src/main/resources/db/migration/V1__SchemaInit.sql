create sequence customer_id_seq start with 1 increment by 50;

create table customers
(
    id    bigint DEFAULT nextval('customer_id_seq') not null,
    email varchar(255)                              not null,
    name  varchar(255)                              not null,
    primary key (id)
);
