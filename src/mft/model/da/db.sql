create table customer
(
    id          number primary key,
    family      nvarchar2(30),
    phoneNumber varchar2(11)
);

create sequence customer_seq start with 1 increment by 1;

create table Event
(
    id       number primary key,
    name     nvarchar2(20),
    category nvarchar2(20),
    price    number,
    quantity number,
    date_time timestamp
);
create sequence Event_seq start with 1 increment by 1;
create table Payment
(
    id       number primary key,
    amount   number,
    date_time timestamp
);
create sequence Payment_seq start with 1 increment by 1;


create table Ticket
(
    id       number primary key,
    event_id references Event,
    info nvarchar2(30),
    date_time timestamp,
    payment_id references Payment,
    customer_id refernces customer
);