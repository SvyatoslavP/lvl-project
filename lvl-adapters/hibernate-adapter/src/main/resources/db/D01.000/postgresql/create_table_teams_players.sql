create table teams_players
(
    team_persistence_id    varchar(255) not null,
    players_persistence_id varchar(255) not null,
    PRIMARY KEY (team_persistence_id, players_persistence_id)
);
alter table teams_players
    add constraint uk_players_persistence_id UNIQUE (players_persistence_id);
alter table teams_players
    add constraint fk_teams_to_players foreign key (players_persistence_id)
        references players;