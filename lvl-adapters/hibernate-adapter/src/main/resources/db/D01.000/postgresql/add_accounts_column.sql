alter table players add account_login varchar(20) not null unique;
alter table players add account_password varchar(20) not null;