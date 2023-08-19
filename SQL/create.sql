use testbooks;
drop table book;
Create table book
(
    id bigint primary key AUTO_INCREMENT,
    name varchar(30),
    pages int,
    rented boolean
)
drop table user;
Create table user
(
    id bigint primary key AUTO_INCREMENT,
    name varchar(30),
    pages int,
    rented boolean
)
