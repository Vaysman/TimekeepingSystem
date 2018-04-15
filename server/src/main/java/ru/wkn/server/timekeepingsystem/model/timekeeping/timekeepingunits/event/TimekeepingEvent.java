package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public abstract class TimekeepingEvent {

    private String time;
    private String date;
    private int employeeID;

    public TimekeepingEvent(String time, String date, int employeeID) {
        this.time = time;
        this.date = date;
        this.employeeID = employeeID;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
