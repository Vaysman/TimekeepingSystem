package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class TaskManager {

    private Dao<Task, List<Task>, Integer> taskDao;

    public TaskManager(Dao<Task, List<Task>, Integer> taskDao) {
        this.taskDao = taskDao;
    }

    public Task createTask(String definition, String startTime, String endTime, String date, int employeeID) throws PersistentException {
        return taskDao.create(new Task(definition, startTime, endTime, date, employeeID));
    }

    public void deleteTask(Task persistentTask) throws PersistentException {
        taskDao.delete(persistentTask);
    }

    public void deleteAll() throws PersistentException {
        for (int i = 0; i < taskDao.getAll().size(); i++) {
            deleteTask(taskDao.getAll().get(i));
        }
    }

    public void editTask(Task oldTask, Task transientTask) throws PersistentException {
        int i = taskDao.getAll().size() - 1;
        String definition = oldTask.getDefinition();
        while (!taskDao.getAll().get(i).getDefinition().equals(definition)) {
            i--;
        }
        taskDao.getAll().get(i).setDefinition(transientTask.getDefinition());
        taskDao.getAll().get(i).setStartTime(transientTask.getStartTime());
        taskDao.getAll().get(i).setEndTime(transientTask.getEndTime());
        taskDao.getAll().get(i).setDate(transientTask.getDate());
        taskDao.getAll().get(i).setAccomplished(transientTask.isAccomplished());
    }
}
