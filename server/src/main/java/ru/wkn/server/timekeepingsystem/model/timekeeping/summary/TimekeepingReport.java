package ru.wkn.server.timekeepingsystem.model.timekeeping.summary;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.Day;

import java.util.List;

public class TimekeepingReport {

    private Dao<Day> dayDao;
    private String date;

    public TimekeepingReport(Dao<Day> dayDao, String date) {
        this.dayDao = dayDao;
        this.date = date;
    }

    public List<Day> getAllDaysReport() throws PersistentException {
        return dayDao.getAll();
    }

    public Day getDayReport(String date) {
        return null;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
