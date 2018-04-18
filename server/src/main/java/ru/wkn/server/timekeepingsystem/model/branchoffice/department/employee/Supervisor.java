package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.timekeeping.managers.EmployeeManager;
import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.TimekeepingReport;

public class Supervisor {

    private Employee employee;
    private EmployeeManager employeeManager;
    private TimekeepingReport timekeepingReport;

    public Supervisor(Employee employee, EmployeeManager employeeManager, TimekeepingReport timekeepingReport) {
        this.employee = employee;
        this.employeeManager = employeeManager;
        this.timekeepingReport = timekeepingReport;
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public TimekeepingReport getTimekeepingReport() {
        return timekeepingReport;
    }
}
