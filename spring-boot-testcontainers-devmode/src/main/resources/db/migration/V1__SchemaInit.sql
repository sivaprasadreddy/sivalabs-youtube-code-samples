create sequence product_id_seq start with 1 increment by 50;

create table products
(
    id          bigint DEFAULT nextval('product_id_seq') not null,
    code        varchar(255)                             not null,
    name        varchar(255)                             not null,
    description varchar(255),
    price       numeric(9, 2)                            not null,
    primary key (id),
    constraint product_code_unique unique (code)
);
