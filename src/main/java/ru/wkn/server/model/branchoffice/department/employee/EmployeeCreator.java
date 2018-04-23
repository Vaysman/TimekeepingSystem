package ru.wkn.server.model.branchoffice.department.employee;

import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.managers.EmployeeManager;
import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.managers.TaskManager;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.summary.TimekeepingLog;
import ru.wkn.server.model.timekeeping.summary.TimekeepingReport;

public class EmployeeCreator {

    private Searcher searcher;

    public EmployeeCreator(Searcher searcher) {
        this.searcher = searcher;
    }

    public EmployeeStatusEnum getEmployeeStatusEnum(EmployeeAuthorizationData employeeAuthorizationData) {
        EmployeeStatusEnum employeeStatusEnum;
        employeeStatusEnum = searcher.getEmployeeStatusByEmployeeAuthorizationData(employeeAuthorizationData);
        return employeeStatusEnum;
    }

    public Employee getEmployee(EmployeeAuthorizationData employeeAuthorizationData) {
        Employee employee;
        employee = searcher.getEmployeeByEmployeeAuthorizationData(employeeAuthorizationData);
        return employee;
    }

    public Supervisor getSupervisor(EmployeeAuthorizationData employeeAuthorizationData) {
        return new Supervisor(getEmployee(employeeAuthorizationData), new EmployeeManager(searcher.getEmployeeDao()), new TimekeepingReport(new DayManager(searcher.getEmployeeDao(), searcher.getTaskDao(), searcher.getEventDao())));
    }

    public Timekeeper getTimekeeper(EmployeeAuthorizationData employeeAuthorizationData) {
        DayManager dayManager = new DayManager(searcher.getEmployeeDao(), searcher.getTaskDao(), searcher.getEventDao());
        return new Timekeeper(getEmployee(employeeAuthorizationData), dayManager, new TaskManager(searcher.getTaskDao()), new TimekeepingLog(dayManager));
    }
}
