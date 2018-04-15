package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import java.util.List;

public class JdbcEventDao implements Dao<TimekeepingEvent> {

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        return null;
    }

    @Override
    public TimekeepingEvent read(int id) throws PersistentException {
        return null;
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        return null;
    }
}
