package ru.wkn.server.model.timekeeping.summary;

import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.Day;

public class TimekeepingLog {

    private DayManager dayManager;

    public TimekeepingLog(DayManager dayManager) {
        this.dayManager = dayManager;
    }

    public Day getDayByDate(String date) throws PersistentException {
        return dayManager.createDay(date);
    }
}
