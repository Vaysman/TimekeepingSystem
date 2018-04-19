package ru.wkn.server.model.timekeeping.timekeepingunits.task;

public class Task {

    private String definition;
    private String startTime;
    private String endTime;
    private String date;
    private int employeeID;
    private boolean isAccomplished;

    public Task(int employeeID, String definition, String startTime, String endTime, String date, boolean isAccomplished) {
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.employeeID = employeeID;
        this.isAccomplished = isAccomplished;
    }

    public String getDefinition() {
        return definition;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }
}
