package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartShift extends TimekeepingEvent {

    private int employeeID;
    private String type;
    private String time;
    private String date;

    public StartShift(int employeeID, String type, String time, String date) {
        super(employeeID, type, time, date);
        this.employeeID = employeeID;
        this.time = time;
        this.date = date;
    }
}
