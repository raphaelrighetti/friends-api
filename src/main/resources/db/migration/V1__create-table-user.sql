create table user(

    id bigint not null auto_increment,
    name varchar(100) not null,
    age int not null,
    email varchar(100) not null unique,
    password varchar(255) not null,

    primary key(id)

);