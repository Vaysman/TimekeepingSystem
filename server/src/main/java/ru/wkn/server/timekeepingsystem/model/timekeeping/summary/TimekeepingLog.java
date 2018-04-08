package ru.wkn.server.timekeepingsystem.model.timekeeping.summary;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.Day;

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
