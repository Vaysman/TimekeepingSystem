package ru.wkn.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.timekeepingsystem.model.dao.Dao;
import ru.wkn.timekeepingsystem.model.timekeeping.log.TimekeepingLog;

public class Timekeeper {

    private Employee employee;
    private Dao<Employee> dao;
    private TimekeepingLog timekeepingLog;
}
