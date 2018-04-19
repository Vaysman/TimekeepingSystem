package ru.wkn.server.model.timekeeping.summary;

import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.timekeepingunits.Day;
import ru.wkn.server.model.dao.persistent.PersistentException;

import java.util.ArrayList;
import java.util.List;

public class TimekeepingReport {

    private DayManager dayManager;

    public TimekeepingReport(DayManager dayManager) {
        this.dayManager = dayManager;
    }

    public Day getDayReport(String date) throws PersistentException {
        return dayManager.createDay(date);
    }

    public List<Day> getAllDaysReport(List<String> dates) throws PersistentException {
        List<Day> days = new ArrayList<>();
        for (String date : dates) {
            days.add(getDayReport(date));
        }
        return days;
    }
}
