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

    public Searcher(Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> eventDao, Dao<Task, List<Task>, Integer> taskDao, Dao<Employee, Employee, Integer> employeeDao) {
        this.eventDao = eventDao;
        this.taskDao = taskDao;
        this.employeeDao = employeeDao;
    }

    public List<TimekeepingEvent> getEventsOfEmployee(int employeeID) {
        List<TimekeepingEvent> events = new ArrayList<>();
        int size;
        try {
            size = eventDao.getAll().size();
            for (int i = 0; i < size; i++) {
                TimekeepingEvent event = eventDao.getAll().get(i);
                if (event.getEmployeeID() == employeeID) {
                    events.add(event);
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Task> getTasksOfEmployee(int employeeID) {
        List<Task> tasks = new ArrayList<>();
        int size;
        try {
            size = taskDao.getAll().size();
            for (int i = 0; i < size; i++) {
                Task task = taskDao.getAll().get(i);
                if (task.getEmployeeID() == employeeID) {
                    tasks.add(task);
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = null;
        try {
            employees = employeeDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeByID(int employeeID) {
        Employee employee = null;
        try {
            employee = employeeDao.read(employeeID);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public EmployeeStatusEnum getEmployeeStatusByEmployeeAuthorizationDataAndStatus(EmployeeAuthorizationData employeeAuthorizationData) {
        EmployeeStatusEnum employeeStatusEnum = null;
        int size;
        try {
            size = employeeDao.getAll().size();
            employeeStatusEnum = EmployeeStatusEnum.EMPLOYEE;
            for (int i = 0; i < size; i++) {
                if (employeeDao.getAll().get(i).getEmployeeAuthorizationData().equals(employeeAuthorizationData)) {
                    employeeStatusEnum = employeeDao.getAll().get(i).getEmployeeStatusEnum();
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return employeeStatusEnum;
    }

    public Employee getEmployeeByEmployeeAuthorizationDataAndStatus(EmployeeAuthorizationData employeeAuthorizationData) {
        Employee employee = null;
        int size;
        try {
            size = employeeDao.getAll().size();
            for (int i = 0; i < size; i++) {
                if (employeeDao.getAll().get(i).getEmployeeAuthorizationData().equals(employeeAuthorizationData)) {
                    employee = employeeDao.getAll().get(i);
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
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
}
