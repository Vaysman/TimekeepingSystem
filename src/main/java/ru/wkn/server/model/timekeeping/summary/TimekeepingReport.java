package ru.wkn.server.model.timekeeping.summary;

import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.timekeepingunits.Day;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

public class TimekeepingReport {

    private DayManager dayManager;

    public TimekeepingReport(DayManager dayManager) {
        this.dayManager = dayManager;
    }

    public Day getDayReport(String date) throws PersistentException {
        return dayManager.createDay(date);
    }
}
