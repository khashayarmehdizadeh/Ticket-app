create table customer(
    id number primary key ,
    name nvarchar2(30),
    family nvarchar2(30),
    gender varchar2(6),
    birth_date date,
    city varchar2(20)
);

create sequence person_seq  start with 1 increment by 1;

create tabl