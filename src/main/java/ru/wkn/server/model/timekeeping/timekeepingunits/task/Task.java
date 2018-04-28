package ru.wkn.server.model.timekeeping.timekeepingunits.task;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private int taskID;
    @Column(name = "employee_id")
    private int employeeID;
    @Column(name = "definition")
    private String definition;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "date_task")
    private String date;
    @Column(name = "status_task")
    private boolean isAccomplished;

    public Task(int employeeID, String definition, String startTime, String endTime, String date, boolean isAccomplished) {
        this.employeeID = employeeID;
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.isAccomplished = isAccomplished;
    }

    public Task(int taskID, int employeeID, String definition, String startTime, String endTime, String date, boolean isAccomplished) {
        this.taskID = taskID;
        this.employeeID = employeeID;
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.isAccomplished = isAccomplished;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }
}
