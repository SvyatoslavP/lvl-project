package ru.panifidkin.lvlproject.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.panifidkin.lvlproject.dao.TeamRepository;
import ru.panifidkin.lvlproject.entity.Team;

import javax.persistence.PersistenceContext;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    @PersistenceContext
    private Session session;

    @Override
    public Team findById(String id) {
        return session.get(Team.class, id);
    }

    @Override
    public void saveOrUpdate(Team team) {
        session.saveOrUpdate(team);
    }

    @Override
    public void delete(Team team) {
        session.delete(team);
    }
}
