create database if not exists ims_test;
create table if not exists ims_test.customers(customer_id int primary key auto_increment, first_name varchar(40), last_name varchar(40));
create table if not exists ims_test.orders(order_id int primary key auto_increment, customer_id int NOT NULL, address varchar(40), foreign key(customer_id) references customers(customer_id));
create table if not exists ims_test.items(item_id int primary key auto_increment, item_name varchar(40), price double);
create table if not exists ims_test.orderline(orderline_id int primary key auto_increment, order_id int NOT NULL, item_id int NOT NULL, foreign key(order_id) references orders(order_id), foreign key(item_id) references items(item_id));