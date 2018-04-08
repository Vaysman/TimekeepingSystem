package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.TimekeepingReport;
import ru.wkn.server.timekeepingsystem.model.dao.Dao;

public class Supervisor {

    private Employee employee;
    private Dao<Employee> dao;
    private TimekeepingReport timekeepingReport;
}
