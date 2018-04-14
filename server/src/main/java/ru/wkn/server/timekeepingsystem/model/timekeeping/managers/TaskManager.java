package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class TaskManager {

    private Dao<Task> taskDao;
    private Dao<Employee> employeeDao;

    public TaskManager(Dao<Task> taskDao, Dao<Employee> employeeDao) {
        this.taskDao = taskDao;
        this.employeeDao = employeeDao;
    }

    public Task createTask(String definition, String startTime, String endTime, String date, int employeeID) throws PersistentException {
        Task task = taskDao.create(new Task(definition, startTime, endTime, date, employeeID));
        employeeDao.read(employeeID).setCurrentTask(task);
        return task;
    }

    public void deleteTask(Task persistentTask) throws PersistentException {
        employeeDao.read(persistentTask.getEmployeeID()).setCurrentTask(null);
        taskDao.delete(persistentTask);
    }

    public void deleteAll() throws PersistentException {
        for (int i = 0; i < taskDao.getAll().size(); i++) {
            deleteTask(taskDao.read(i));
        }
    }

    public void deleteOnlyFromDatabase() throws PersistentException {
        for (int i = 0; i < taskDao.getAll().size(); i++) {
            taskDao.delete(taskDao.read(i));
        }
    }

    public void editTask(Task transientTask, Task newTask) throws PersistentException {
        int i = taskDao.getAll().size() - 1;
        String definition = transientTask.getDefinition();
        while (!taskDao.getAll().get(i).getDefinition().equals(definition)) {
            i--;
        }
        taskDao.getAll().get(i).setDefinition(newTask.getDefinition());
        taskDao.getAll().get(i).setStartTime(newTask.getStartTime());
        taskDao.getAll().get(i).setEndTime(newTask.getEndTime());
        taskDao.getAll().get(i).setDate(newTask.getDate());
        taskDao.getAll().get(i).setAccomplished(newTask.isAccomplished());
    }
}
