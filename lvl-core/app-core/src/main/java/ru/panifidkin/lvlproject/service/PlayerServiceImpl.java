package ru.panifidkin.lvlproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.panifidkin.lvlproject.dao.PlayerRepository;
import ru.panifidkin.lvlproject.entity.Player;
import ru.panifidkin.lvlproject.services.PlayerService;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repo;

    @Override
    public Player findById(String id) {
        return repo.findById(id);
    }

    @Override
    public void saveOrUpdate(Player player) {
        repo.saveOrUpdate(player);
    }

    @Override
    public void delete(Player player) {
        repo.delete(player);
    }
}
