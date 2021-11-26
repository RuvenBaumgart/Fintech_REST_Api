create table credits (
  id varchar(36) not null,
  current_credit_amount_in_cents bigint,
  customer_id varchar(36),
  data_of_creation date,
  original_term_in_months bigint,
  orignial_credit_amount_in_cents bigint,
  remaining_term_in_months bigint,
  primary key (id)
);