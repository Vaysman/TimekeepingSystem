package ru.wkn.server.model.branchoffice.department.employee;

import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.managers.EmployeeManager;
import ru.wkn.server.model.timekeeping.managers.TimekeepingEventManager;
import ru.wkn.server.model.branchoffice.department.Department;
import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.managers.TaskManager;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.summary.TimekeepingLog;
import ru.wkn.server.model.timekeeping.summary.TimekeepingReport;

public class EmployeeCreator {

    public Employee createEmployee(int employeeID, String name, String surname, String telephoneNumber, EmployeeStatusEnum employeeStatusEnum, EmployeeAuthorizationData employeeAuthorizationData, Department department, TimekeepingEventManager timekeepingEventManager, Searcher searcher) {
        return new Employee(employeeID, name, surname, telephoneNumber, employeeStatusEnum.toString(), employeeAuthorizationData.getLogin(), employeeAuthorizationData.getPassword(), department.getDepartmentName(), department.getBranchOffice().getBranchOfficeName(), timekeepingEventManager, searcher);
    }

    public Supervisor createSupervisor(Employee employee, EmployeeManager employeeManager, TimekeepingReport timekeepingReport) {
        return new Supervisor(employee, employeeManager, timekeepingReport);
    }

    public Timekeeper createTimekeeper(Employee employee, DayManager dayManager, TaskManager taskManager, TimekeepingLog timekeepingLog) {
        return new Timekeeper(employee, dayManager, taskManager, timekeepingLog);
    }
}
