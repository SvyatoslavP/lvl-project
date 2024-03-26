package ru.panifidkin.lvlproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.panifidkin.lvlproject.dao.TeamRepository;
import ru.panifidkin.lvlproject.entity.Team;
import ru.panifidkin.lvlproject.services.TeamService;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repo;

    @Override
    public Team findById(String id) {
        return repo.findById(id);
    }

    @Override
    public void saveOrUpdate(Team team) {
        repo.saveOrUpdate(team);
    }

    @Override
    public void delete(Team team) {
        repo.delete(team);
    }

    @Override
    public Team findByName(String name) {
        return repo.findByName(name);
    }
}
