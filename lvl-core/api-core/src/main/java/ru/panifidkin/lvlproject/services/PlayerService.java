package ru.panifidkin.lvlproject.services;

import ru.panifidkin.lvlproject.entity.Player;

public interface PlayerService {
    Player findById(String id);
    void saveOrUpdate(Player player);
    void delete(Player player);
}
