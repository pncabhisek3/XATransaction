use database sbi_database;
use database hdfc_database;

create table if not exists sbi_account(
	_id int(5) primary key auto_increment,
	amount DECIMAL(5,2) default 0.00
);

create table if not exists hdfc_account(
	_id int(5) primary key auto_increment,
	amount DECIMAL(5,2) default 0.00
);