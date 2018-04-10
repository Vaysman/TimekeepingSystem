package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class TaskManager {

    private Dao<Task> taskDao;

    public Task createTask(String definition, String startTime, String endTime) {
        return new Task(definition, startTime, endTime);
    }
}
