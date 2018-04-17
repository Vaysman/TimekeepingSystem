package ru.wkn.server.timekeepingsystem.model.timekeeping.summary;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private Dao<TimekeepingEvent> eventDao;
    private Dao<Task> taskDao;
    private Dao<Employee> employeeDao;
    private int employeeID;

    public Searcher(Dao<TimekeepingEvent> eventDao, Dao<Task> taskDao, Dao<Employee> employeeDao, int employeeID) {
        this.eventDao = eventDao;
        this.taskDao = taskDao;
        this.employeeDao = employeeDao;
        this.employeeID = employeeID;
    }

    public List<TimekeepingEvent> getEventsOfEmployee() throws PersistentException {
        List<TimekeepingEvent> events = new ArrayList<>();
        int size = eventDao.getAll().size();
        for (int i = 0; i < size; i++) {
            TimekeepingEvent temp = eventDao.read(i);
            if (temp.getEmployeeID() == employeeID) {
                events.add(temp);
            }
        }
        return events;
    }

    public List<Task> getTasksOfEmployee() throws PersistentException {
        List<Task> temp = new ArrayList<>();
        int size = taskDao.getAll().size();
        for (int i = 0; i < size; i++) {
            Task task = taskDao.read(i);
            if (task.getEmployeeID() == employeeID) {
                temp.add(task);
            }
        }
        return temp;
    }

    public List<Employee> getEmployees() throws PersistentException {
        return employeeDao.getAll();
    }

    public Employee getEmployeeByID(int employeeID) throws PersistentException {
        return employeeDao.read(employeeID);
    }
}
