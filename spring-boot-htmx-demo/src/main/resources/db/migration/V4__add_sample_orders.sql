insert into orders (id,order_number,username,
                    customer_name,customer_email,customer_phone,
                    delivery_address_line1,delivery_address_line2,delivery_address_city,
                    delivery_address_state,delivery_address_zip_code,delivery_address_country,
                    status,comments) values
(1, 'order-001', 'user', 'Siva', 'siva@gmail.com', '11111111', '123 Main St', 'Apt 1', 'Dallas', 'TX', '75001', 'USA', 'NEW', null),
(2, 'order-002', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(3, 'order-003', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(4, 'order-004', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(5, 'order-005', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(6, 'order-006', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(7, 'order-007', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(8, 'order-008', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(9, 'order-009', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(10, 'order-010', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(11, 'order-011', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(12, 'order-012', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null),
(13, 'order-013', 'user', 'Prasad', 'prasad@gmail.com', '2222222', '123 Main St', 'Apt 1', 'Hyderabad', 'TS', '500072', 'India', 'NEW', null)
;

insert into order_items(order_id, code, name, price, quantity) values
(1, 'P100', 'The Hunger Games', 34.0, 2),
(1, 'P101', 'To Kill a Mockingbird', 45.40, 1),
(2, 'P102', 'The Chronicles of Narnia', 44.50, 1),
(3, 'P100', 'The Hunger Games', 34.0, 2),
(4, 'P100', 'The Hunger Games', 34.0, 2),
(5, 'P100', 'The Hunger Games', 34.0, 2),
(6, 'P100', 'The Hunger Games', 34.0, 2),
(7, 'P100', 'The Hunger Games', 34.0, 2),
(8, 'P100', 'The Hunger Games', 34.0, 2),
(9, 'P100', 'The Hunger Games', 34.0, 2),
(10, 'P100', 'The Hunger Games', 34.0, 2),
(11, 'P100', 'The Hunger Games', 34.0, 2),
(12, 'P100', 'The Hunger Games', 34.0, 2),
(13, 'P100', 'The Hunger Games', 34.0, 2)
;