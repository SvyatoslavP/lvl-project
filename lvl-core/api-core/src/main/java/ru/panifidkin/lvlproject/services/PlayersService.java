package ru.panifidkin.lvlproject.services;

import ru.panifidkin.lvlproject.entity.Player;

public interface PlayersService {
    Player findOne(String playerId);
}
