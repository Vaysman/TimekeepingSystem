package ru.wkn.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.timekeepingsystem.model.branchoffice.department.employee.status.EmployeeStatus;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class Employee {

    private String name;
    private String surname;
    private EmployeeStatus employeeStatus;
    private Task task;
    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
}