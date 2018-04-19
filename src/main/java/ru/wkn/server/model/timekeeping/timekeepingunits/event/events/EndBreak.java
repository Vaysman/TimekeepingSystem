package ru.wkn.server.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class EndBreak extends TimekeepingEvent {

    private int employeeID;
    private String type;
    private String time;
    private String date;

    public EndBreak(int employeeID, String type, String time, String date) {
        super(employeeID, type, time, date);
        this.employeeID = employeeID;
        this.time = time;
        this.date = date;
    }
}
