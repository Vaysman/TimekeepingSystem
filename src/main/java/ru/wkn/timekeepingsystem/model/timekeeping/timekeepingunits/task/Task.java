package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.task;

import java.util.Objects;

public class Task {

    private String definition;

    public Task(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(definition, task.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition);
    }

    @Override
    public String toString() {
        return "Task{" +
                "definition='" + definition + '\'' +
                '}';
    }
}
