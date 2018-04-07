package ru.wkn.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.timekeepingsystem.model.dao.Dao;
import ru.wkn.timekeepingsystem.model.timekeeping.log.TimekeepingReport;

public class Supervisor {

    private Employee employee;
    private Dao<Employee> dao;
    private TimekeepingReport timekeepingReport;
}
