package ru.wkn.server.model.timekeeping.timekeepingunits;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class Day {

    private String date;
    private List<Employee> employees;
    private List<Task> tasks;
    private List<TimekeepingEvent> events;

    public Day(String date, List<Employee> employees, List<Task> tasks, List<TimekeepingEvent> events) {
        this.date = date;
        this.employees = employees;
        this.tasks = tasks;
        this.events = events;
    }
}
