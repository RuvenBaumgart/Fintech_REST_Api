create table accounts (
  id varchar(36) not null,
  balance_in_cent bigint,
  customer_id varchar(36),
  sum_of_transactions bigint,
  primary key (id)
);


