package ru.wkn.server.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartBreak extends TimekeepingEvent {

    private int eventID;
    private int employeeID;
    private String type;
    private String time;
    private String date;

    public StartBreak(int employeeID, String type, String time, String date) {
        super(employeeID, type, time, date);
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public StartBreak(int eventID, int employeeID, String type, String time, String date) {
        super(eventID, employeeID, type, time, date);
        this.eventID = eventID;
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }
}
