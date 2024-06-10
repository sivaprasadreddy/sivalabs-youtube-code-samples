create sequence order_id_seq start with 1000 increment by 50;

create table orders
(
    id             bigint default nextval('order_id_seq') not null,
    order_number   text                                   not null unique,
    customer_name  text                                   not null,
    customer_email text                                   not null,
    customer_phone text                                   not null,
    product_code   text                                   not null,
    product_name   text                                   not null,
    product_price  text                                   not null,
    quantity       int                                    not null,
    status         text                                   not null,
    comments       text,
    created_at     timestamp,
    updated_at     timestamp,
    primary key (id)
);
