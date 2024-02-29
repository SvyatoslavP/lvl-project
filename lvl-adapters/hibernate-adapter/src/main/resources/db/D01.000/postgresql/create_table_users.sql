create table players
(
    persistence_id varchar(255) not null unique,
    first_name     varchar(255) not null,
    last_name      varchar(255) not null,
    birth_date     date not null,
    primary key (persistence_id)
);