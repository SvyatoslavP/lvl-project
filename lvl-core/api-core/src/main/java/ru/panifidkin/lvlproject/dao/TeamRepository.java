package ru.panifidkin.lvlproject.dao;

import ru.panifidkin.lvlproject.entity.Team;

public interface TeamRepository {

    Team findById(String id);

    void saveOrUpdate(Team team);

    void delete(Team team);

    Team findByName(String name);
}
