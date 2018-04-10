package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class Day {

    private String date;
    private List<Employee> employees;
    private List<Task> tasks;
    private List<TimekeepingEvent> events;
}
