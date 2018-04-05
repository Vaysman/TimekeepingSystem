package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.task;

import java.util.Objects;

public class Task {

    private String definition;
    private String startTime;
    private String endTime;

    public Task(String definition, String startTime, String endTime) {
        this.definition = definition;
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(definition, task.definition) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Task{" +
                "definition='" + definition + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}