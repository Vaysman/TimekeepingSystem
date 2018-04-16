package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartBreak extends TimekeepingEvent {

    private int employeeID;
    private String time;
    private String date;

    public StartBreak(int employeeID, String time, String date) {
        super(employeeID, time, date);
        this.employeeID = employeeID;
        this.time = time;
        this.date = date;
    }
}
