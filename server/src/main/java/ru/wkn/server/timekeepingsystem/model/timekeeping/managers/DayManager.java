package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.Day;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import java.util.ArrayList;
import java.util.List;

public class DayManager {

    private Dao<Employee> employeeDao;
    private Dao<Task> taskDao;
    private Dao<TimekeepingEvent> timekeepingEventDao;

    public DayManager(Dao<Employee> employeeDao, Dao<Task> taskDao, Dao<TimekeepingEvent> timekeepingEventDao) {
        this.employeeDao = employeeDao;
        this.taskDao = taskDao;
        this.timekeepingEventDao = timekeepingEventDao;
    }

    public Day createDay(String date) throws PersistentException {
        List<Employee> employees = employeeDao.getAll();
        List<Task> tasks = new ArrayList<>();
        List<TimekeepingEvent> events = new ArrayList<>();
        int size = taskDao.getAll().size();
        for (int i = 0; i < size; i++) {
            Task temp = taskDao.read(i);
            if (temp.getDate().equals(date)) {
                tasks.add(temp);
            }
        }
        size = timekeepingEventDao.getAll().size();
        for (int i = 0; i < size; i++) {
            TimekeepingEvent temp = timekeepingEventDao.read(i);
            if (temp.getDate().equals(date)) {
                events.add(temp);
            }
        }
        return new Day(date, employees, tasks, events);
    }
}
