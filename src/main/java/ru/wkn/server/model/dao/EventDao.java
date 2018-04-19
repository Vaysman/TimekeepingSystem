package ru.wkn.server.model.dao;

import ru.wkn.server.model.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import java.util.List;

public class EventDao implements Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> {

    private JdbcEventDao jdbcEventDao;

    public EventDao(JdbcEventDao jdbcEventDao) {
        this.jdbcEventDao = jdbcEventDao;
    }

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        jdbcEventDao.create(newInstance);
        return newInstance;
    }

    @Override
    public List<TimekeepingEvent> read(Integer id) throws PersistentException {
        return jdbcEventDao.read(id);
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        jdbcEventDao.update(transientObject);
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        jdbcEventDao.delete(persistentObject);
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        return jdbcEventDao.getAll();
    }
}
