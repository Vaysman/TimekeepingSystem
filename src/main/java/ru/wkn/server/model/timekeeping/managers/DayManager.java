package ru.wkn.server.model.timekeeping.managers;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.dao.Dao;
import ru.wkn.server.model.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.Day;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import java.util.ArrayList;
import java.util.List;

public class DayManager {

    private Dao<Employee, Employee, Integer> employeeDao;
    private Dao<Task, List<Task>, Integer> taskDao;
    private Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> timekeepingEventDao;

    public DayManager(Dao<Employee, Employee, Integer> employeeDao, Dao<Task, List<Task>, Integer> taskDao, Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> timekeepingEventDao) {
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
            Task temp = taskDao.getAll().get(i);
            if (temp.getDate().equals(date)) {
                tasks.add(temp);
            }
        }
        size = timekeepingEventDao.getAll().size();
        for (int i = 0; i < size; i++) {
            TimekeepingEvent temp = timekeepingEventDao.getAll().get(i);
            if (temp.getDate().equals(date)) {
                events.add(temp);
            }
        }
        return new Day(date, employees, tasks, events);
    }
}
