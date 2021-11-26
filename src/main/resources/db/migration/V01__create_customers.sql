create table customers (
    id varchar(255) not null,
    birthday date,
    firstname varchar(255),
    rating integer,
    salutation varchar(255),
    secondname varchar(255),
    address_id varchar(255),
    primary key (id)
);

create table addresses (
    id varchar(255) not null,
    customer_id varchar(255),
    city varchar(255),
    country varchar(255),
    province varchar(255),
    street varchar(255),
    zip_code varchar(255),
    primary key (id)
);

alter table customers 
    add constraint FKm07rwxbyxjv8r4fcye1ff6rmq 
    foreign key (address_id) 
    references addresses
