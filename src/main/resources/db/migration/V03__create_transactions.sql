create table transactions (
  id varchar(36) not null,
  amount_in_cent bigint,
  destination_accoutn_id varchar(36),
  message varchar(255),
  source_account_id varchar(36),
  transaction_date date,
  transaction_time time,
  primary key (id)
);

create table account_entity_transaction_ids (
  account_entity_id varchar(36) not null,
  transaction_ids varchar(36)
);

alter table account_entity_transaction_ids 
  add constraint account_entity_transaction_id_account_fk
  foreign key (account_entity_id) 
  references accounts 