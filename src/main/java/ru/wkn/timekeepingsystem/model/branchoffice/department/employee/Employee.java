package ru.wkn.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class Employee {

    private String name;
    private String surname;
    private Task task;
    private TimekeepingEvent timekeepingEvent;
}
