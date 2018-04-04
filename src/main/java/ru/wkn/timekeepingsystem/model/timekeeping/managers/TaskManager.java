package ru.wkn.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class TaskManager {

    public Task createTask(String definition) {
        return new Task(definition);
    }
}
