package ru.panifidkin.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.panifidkin.lvlproject.dao.PlayerRepository;
import ru.panifidkin.lvlproject.entity.Player;

import javax.persistence.PersistenceContext;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    @PersistenceContext
    private Session session;

    public Player findById(String id) {
        return session.get(Player.class, id);
    }

    public void saveOrUpdate(Player player) {
        session.saveOrUpdate(player);
    }

    public void delete(Player player) {
        session.delete(player);
    }

}
