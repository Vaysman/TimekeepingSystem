package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

import java.util.List;

public class JdbcEventDao implements Dao<TimekeepingEventIF> {

    @Override
    public TimekeepingEventIF create(TimekeepingEventIF newInstance) throws PersistentException {
        return null;
    }

    @Override
    public TimekeepingEventIF read(int id) throws PersistentException {
        return null;
    }

    @Override
    public void update(TimekeepingEventIF transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(TimekeepingEventIF persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<TimekeepingEventIF> getAll() throws PersistentException {
        return null;
    }
}
