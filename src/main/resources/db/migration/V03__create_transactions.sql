create table transactions (
  id varchar(255) not null,
  amount_in_cent bigint,
  destination_accoutn_id varchar(255),
  message varchar(255),
  source_account_id varchar(255),
  transaction_date date,
  transaction_time time,
  receiver_id varchar(255) not null,
  sender_id varchar(255) not null,
  primary key (id)
);

alter table account_entity_transaction_ids 
  add constraint account_entity_transaction_id_account_fk
  foreign key (account_entity_id) 
  references accounts(id);

alter table transactions 
  add constraint FKjord7to517f34oa9ni6puyt85 
  foreign key (receiver_id) 
  references accounts;

alter table transactions 
  add constraint FKep3ko5p4fdvnw79p1uhw2q6nf 
  foreign key (sender_id) 
  references accounts;

