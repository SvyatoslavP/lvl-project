create table teams
(
    persistence_id varchar(255)   not null unique,
    team_name      varchar(255)   not null,
    team_rating    numeric(17, 2) not null,
    founding_date  date           not null,
    primary key (persistence_id)
);