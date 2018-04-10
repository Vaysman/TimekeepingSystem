package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.Day;

import java.util.List;

public class DayDao implements Dao<Day> {

    private JdbcDayDao jdbcDayDao;

    public DayDao(JdbcDayDao jdbcDayDao) {
        this.jdbcDayDao = jdbcDayDao;
    }

    @Override
    public Day create(Day newInstance) throws PersistentException {
        return jdbcDayDao.create(newInstance);
    }

    @Override
    public Day read(int id) throws PersistentException {
        return jdbcDayDao.read(id);
    }

    @Override
    public void update(Day transientObject) throws PersistentException {
        jdbcDayDao.update(transientObject);
    }

    @Override
    public void delete(Day persistentObject) throws PersistentException {
        jdbcDayDao.delete(persistentObject);
    }

    @Override
    public List<Day> getAll() throws PersistentException {
        return jdbcDayDao.getAll();
    }
}
