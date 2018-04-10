package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.Day;

import java.util.List;

public class JdbcDayDao implements Dao<Day> {

    @Override
    public Day create(Day newInstance) throws PersistentException {
        return null;
    }

    @Override
    public Day read(int id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Day transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(Day persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<Day> getAll() throws PersistentException {
        return null;
    }
}
