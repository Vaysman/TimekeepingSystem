package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

import java.util.List;

public class EventDao implements Dao<TimekeepingEventIF> {

    private JdbcEventDao jdbcEventDao;

    public EventDao(JdbcEventDao jdbcEventDao) {
        this.jdbcEventDao = jdbcEventDao;
    }

    @Override
    public TimekeepingEventIF create(TimekeepingEventIF newInstance) throws PersistentException {
        jdbcEventDao.create(newInstance);
        return newInstance;
    }

    @Override
    public TimekeepingEventIF read(int id) throws PersistentException {
        return jdbcEventDao.read(id);
    }

    @Override
    public void update(TimekeepingEventIF transientObject) throws PersistentException {
        jdbcEventDao.update(transientObject);
    }

    @Override
    public void delete(TimekeepingEventIF persistentObject) throws PersistentException {
        jdbcEventDao.delete(persistentObject);
    }

    @Override
    public List<TimekeepingEventIF> getAll() throws PersistentException {
        return jdbcEventDao.getAll();
    }
}
