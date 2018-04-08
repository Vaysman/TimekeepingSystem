package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.TimekeepingLog;

public class Timekeeper {

    private Employee employee;
    private Dao<Employee> dao;
    private TimekeepingLog timekeepingLog;
}
