package ru.wkn.server.model.timekeeping.summary;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;
import ru.wkn.server.model.dao.Dao;
import ru.wkn.server.model.dao.persistent.PersistentException;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> eventDao;
    private Dao<Task, List<Task>, Integer> taskDao;
    private Dao<Employee, Employee, Integer> employeeDao;
    private int employeeID;

    public Searcher(Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> eventDao, Dao<Task, List<Task>, Integer> taskDao, Dao<Employee, Employee, Integer> employeeDao, int employeeID) {
        this.eventDao = eventDao;
        this.taskDao = taskDao;
        this.employeeDao = employeeDao;
        this.employeeID = employeeID;
    }

    public List<TimekeepingEvent> getEventsOfEmployee() throws PersistentException {
        List<TimekeepingEvent> events = new ArrayList<>();
        int size = eventDao.getAll().size();
        for (int i = 0; i < size; i++) {
            TimekeepingEvent event = eventDao.getAll().get(i);
            if (event.getEmployeeID() == employeeID) {
                events.add(event);
            }
        }
        return events;
    }

    public List<Task> getTasksOfEmployee() throws PersistentException {
        List<Task> tasks = new ArrayList<>();
        int size = taskDao.getAll().size();
        for (int i = 0; i < size; i++) {
            Task task = taskDao.getAll().get(i);
            if (task.getEmployeeID() == employeeID) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<Employee> getEmployees() throws PersistentException {
        return employeeDao.getAll();
    }

    public Employee getEmployeeByID(int employeeID) throws PersistentException {
        return employeeDao.read(employeeID);
    }

    public EmployeeStatusEnum getEmployeeStatus(EmployeeAuthorizationData employeeAuthorizationData) throws PersistentException {
        int size = employeeDao.getAll().size();
        EmployeeStatusEnum employeeStatusEnum = EmployeeStatusEnum.EMPLOYEE;
        for (int i = 0; i < size; i++) {
            if (employeeDao.getAll().get(i).getEmployeeAuthorizationData().equals(employeeAuthorizationData)) {
                employeeStatusEnum = employeeDao.getAll().get(i).getEmployeeStatusEnum();
            }
        }
        return employeeStatusEnum;
    }

    public Employee getEmployeeByEmployeeAuthorizationDataAndStatus(EmployeeAuthorizationData employeeAuthorizationData) throws PersistentException {
        int size = employeeDao.getAll().size();
        Employee employee = null;
        for (int i = 0; i < size; i++) {
            if (employeeDao.getAll().get(i).getEmployeeAuthorizationData().equals(employeeAuthorizationData)) {
                employee = employeeDao.getAll().get(i);
            }
        }
        return employee;
    }

    public Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> getEventDao() {
        return eventDao;
    }

    public Dao<Task, List<Task>, Integer> getTaskDao() {
        return taskDao;
    }

    public Dao<Employee, Employee, Integer> getEmployeeDao() {
        return employeeDao;
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
