create table customer
(
    id         number primary key,
    name       nvarchar2(30),
    family     nvarchar2(30),
    gender     varchar2(6),
    birth_date date,
    city       varchar2(20)
);

create sequence customer_seq start with 1 increment by 1;

create table Event
(
    id       number primary key,
    name     nvarchar2(20),
    category nvarchar2(20),
    price    number,
    quantity number,
    datetime date
);
create sequence Event_seq start with 1 increment by 1;
create table Payment
(
    id       number primary key,
    amount   number,
    datetime date
);
create sequence Payment_seq start with 1 increment by 1;