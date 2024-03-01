package ru.panifidkin.lvlproject.dao;

import ru.panifidkin.lvlproject.entity.Player;

public interface PlayerRepository {
    Player findById(String id);
    void saveOrUpdate(Player player);
    void delete(Player player);
}
