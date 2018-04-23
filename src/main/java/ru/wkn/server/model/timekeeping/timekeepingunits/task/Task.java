package ru.wkn.server.model.timekeeping.timekeepingunits.task;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    private int taskID;
    private int employeeID;
    private String definition;
    private String startTime;
    private String endTime;
    private String date;
    private boolean isAccomplished;

    public Task(int taskID, int employeeID, String definition, String startTime, String endTime, String date, boolean isAccomplished) {
        this.taskID = taskID;
        this.employeeID = employeeID;
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.isAccomplished = isAccomplished;
    }

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    @Column(name = "employee_id")
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Column(name = "definition")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name = "date_task")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "status_task")
    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }
}
