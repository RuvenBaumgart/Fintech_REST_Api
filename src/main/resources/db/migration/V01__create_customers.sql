create table customers (
  id varchar(36) not null,
  birthday date,
  firstname varchar(255) not null,
  rating integer,
  salutation varchar(255) not null,
  secondname varchar(255) not null,
  primary key (id)
);

create table addresses (
  id varchar(36) not null,
  city varchar(255),
  country varchar(255),
  customer_id varchar(36),
  province varchar(255),
  street varchar(255),
  zip_code varchar(255),
  primary key (id)
);
