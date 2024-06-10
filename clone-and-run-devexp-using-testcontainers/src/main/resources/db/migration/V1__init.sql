create table products (
    id BIGSERIAL PRIMARY KEY,
    code varchar not null UNIQUE,
    name varchar not null,
    price numeric(5,2) not null
);

insert into products(code, name, price) values ('P100', 'Product One', 200);
insert into products(code, name, price) values ('P200', 'Product Two', 350);
