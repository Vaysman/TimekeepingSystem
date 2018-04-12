package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class DayManager {

    private Dao<Employee> employeeDao;
    private Dao<Task> taskDao;
    private Dao<TimekeepingEventIF> timekeepingEventDao;
}
