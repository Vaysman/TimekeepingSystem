package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public abstract class TimekeepingEvent {

    private int employeeID;
    private String time;
    private String date;

    public TimekeepingEvent(int employeeID, String time, String date) {
        this.employeeID = employeeID;
        this.time = time;
        this.date = date;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
