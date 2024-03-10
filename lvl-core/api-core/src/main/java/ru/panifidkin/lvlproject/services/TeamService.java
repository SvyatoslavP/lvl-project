package ru.panifidkin.lvlproject.services;

import ru.panifidkin.lvlproject.entity.Team;

public interface TeamService {

    Team findById(String id);

    void saveOrUpdate(Team team);

    void delete(Team team);
}
