Create database "debitwallet"

Execute these queries

create table debit_card (card_number varchar(255) not null, balance bigint not null, card_holder_name varchar(255), expiration_month bigint, expiration_year bigint, security_code integer, primary key (card_number))

create table debit_card_Transaction (DebitCard_card_number varchar(255) not null, transactionList_id bigint not null)

create table Transaction (id bigint not null auto_increment, transaction_amount bigint, transaction_type varchar(255), primary key (id))

create table User (email varchar(255) not null, name varchar(255), password varchar(255), debitCard_card_number varchar(255), primary key (email))
