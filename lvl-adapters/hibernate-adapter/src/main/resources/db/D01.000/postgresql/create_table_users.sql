create table users
(
    persistence_id varchar(255) not null unique,
    first_name     varchar(255) not null,
    primary key (persistence_id)
);