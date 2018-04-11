package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task;

import java.util.Objects;

public class Task {

    private String definition;
    private String startTime;
    private String endTime;
    private int employeeID;
    private boolean isAccomplished;

    public Task(String definition, String startTime, String endTime, int employeeID) {
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeeID = employeeID;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return employeeID == task.employeeID &&
                isAccomplished == task.isAccomplished &&
                Objects.equals(definition, task.definition) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition, startTime, endTime, employeeID, isAccomplished);
    }

    @Override
    public String toString() {
        return "Task{" +
                "definition='" + definition + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", employeeID=" + employeeID +
                ", isAccomplished=" + isAccomplished +
                '}';
    }
}
