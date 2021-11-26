create table account_entity_transaction_ids (
  account_entity_id varchar(255) not null,
  transaction_ids varchar(255)
);


create table accounts (
  id varchar(36) not null,
  balance_in_cent bigint,
  customer_id varchar(36),
  sum_of_transactions bigint,
  primary key (id)
);


alter table account_entity_transaction_ids 
  add constraint FKgy48aurreoca93wi943hcksls 
  foreign key (account_entity_id) 
  references accounts


