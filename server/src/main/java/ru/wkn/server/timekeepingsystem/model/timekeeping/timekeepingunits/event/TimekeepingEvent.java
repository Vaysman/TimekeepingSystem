package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public abstract class TimekeepingEvent {

    private int employeeID;
    private String type;
    private String time;
    private String date;

    public TimekeepingEvent(int employeeID, String type, String time, String date) {
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
