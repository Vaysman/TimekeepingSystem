package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.branchoffice.BranchOffice;
import ru.wkn.server.timekeepingsystem.model.branchoffice.department.Department;
import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.timekeepingsystem.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.timekeepingsystem.model.timekeeping.managers.TimekeepingEventManager;
import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.CalendarEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class Employee {

    private int employeeID;
    private String name;
    private String surname;
    private String telephoneNumber;
    private EmployeeStatusEnum employeeStatusEnum;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Department department;
    private BranchOffice branchOffice;
    private Task currentTask;
    private TimekeepingEventManager timekeepingEventManager;
    private CalendarEvent calendarEvent;

    public Employee(int employeeID, String name, String surname, String telephoneNumber, String employeeStatus, String login, String password, String department, String branchOffice, Task currentTask, TimekeepingEventManager timekeepingEventManager, CalendarEvent calendarEvent) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.employeeStatusEnum = EmployeeStatusEnum.valueOf(employeeStatus);
        this.employeeAuthorizationData = new EmployeeAuthorizationData(login, password);
        this.department = new Department(department);
        this.branchOffice = new BranchOffice(branchOffice);
        this.currentTask = currentTask;
        this.timekeepingEventManager = timekeepingEventManager;
        this.calendarEvent = calendarEvent;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public EmployeeStatusEnum getEmployeeStatusEnum() {
        return employeeStatusEnum;
    }

    public void setEmployeeStatusEnum(String employeeStatusEnum) {
        this.employeeStatusEnum = EmployeeStatusEnum.valueOf(employeeStatusEnum);
    }

    public EmployeeAuthorizationData getEmployeeAuthorizationData() {
        return employeeAuthorizationData;
    }

    public void setEmployeeAuthorizationData(EmployeeAuthorizationData employeeAuthorizationData) {
        this.employeeAuthorizationData = new EmployeeAuthorizationData(employeeAuthorizationData.getLogin(), employeeAuthorizationData.getPassword());
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public TimekeepingEventManager getTimekeepingEventManager() {
        return timekeepingEventManager;
    }

    public void setTimekeepingEventManager(TimekeepingEventManager timekeepingEventManager) {
        this.timekeepingEventManager = timekeepingEventManager;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }

    public void setCalendarEvent(CalendarEvent calendarEvent) {
        this.calendarEvent = calendarEvent;
    }
}
