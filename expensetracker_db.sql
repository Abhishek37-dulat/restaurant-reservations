drop database expensetrackerdb;
drop user expensetracker;
create user expensetracker with password 'password';
create database expensetrackerdb with template=template0 owner=expensetracker;
\connect expensetrackerdb;
alter default privileges grant all on tables to expensetracker;
alter default privileges grant all on sequences to expensetracker;
create table et_users (
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table et_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(28) not null,
description varchar(50) not null
);

alter table et_categories add constraint cat_users_fk
foreign key (user_id) references et_users (user_id);

create table et_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50) not null,
transaction_date bigint not null
);

create table et_restaurants (
    restaurant_id integer primary key not null,
    name varchar(50) not null,
    location varchar(100) not null,
    cuisines varchar(255)[],
    working_days jsonb,
    time_slot_interval int,
    image_url varchar(255),
    capacity jsonb
);

create table et_tables (
    table_id integer primary key not null,
    restaurant_id integer not null,
    table_type varchar(20) not null,
    number_of_tables integer not null,
    foreign key (restaurant_id) references et_restaurants (restaurant_id)
);

alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories (category_id);
alter table et_transactions add constraint trans_users_fk
foreign key (user_id) references et_users(user_id);


create sequence et_users_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;
create sequence et_restaurants_seq increment 1 start 1;
create sequence et_tables_seq increment 1 start 1;