package ru.wkn.server.model.timekeeping.summary;

import ru.wkn.server.model.timekeeping.timekeepingunits.Day;
import ru.wkn.server.model.dao.Dao;
import ru.wkn.server.model.dao.persistent.PersistentException;

public class TimekeepingLog {

    private Dao<Day> dayDao;

    public TimekeepingLog(Dao<Day> dayDao) {
        this.dayDao = dayDao;
    }

    public void add(Day day) throws PersistentException {
        dayDao.create(day);
    }

    public Day read(int id) throws PersistentException {
        return dayDao.read(id);
    }

    public void update(Day day) throws PersistentException {
        dayDao.update(day);
    }

    public void delete(Day day) throws PersistentException {
        dayDao.delete(day);
    }
}
