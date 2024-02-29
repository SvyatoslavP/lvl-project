package ru.panifidkin.lvlproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.services.PlayersService;

import javax.annotation.Nullable;

@Slf4j
@Service
@Transactional
public class PlayersServiceImpl implements PlayersService {

    @Nullable
    @Override
    public Player findOne(@Nullable String playerId) {
        return null;
    }
}
