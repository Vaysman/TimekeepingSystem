package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.branchoffice.BranchOffice;
import ru.wkn.server.timekeepingsystem.model.branchoffice.department.Department;
import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.status.EmployeeStatus;
import ru.wkn.server.timekeepingsystem.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.CalendarEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

public class Employee {

    private int employeeID;
    private String name;
    private String surname;
    private String telephoneNumber;
    private EmployeeStatus employeeStatus;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Department department;
    private BranchOffice branchOffice;
    private Task currentTask;
    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
    private CalendarEvent calendarEvent;

    public Employee(int employeeID, String name, String surname, String telephoneNumber, String employeeStatus, String login, String password, String department, String branchOffice, Task currentTask, EventFactoryIF<TimekeepingEvent> eventEventFactoryIF, CalendarEvent calendarEvent) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.employeeStatus = EmployeeStatus.valueOf(employeeStatus);
        this.employeeAuthorizationData = new EmployeeAuthorizationData(login, password);
        this.department = new Department(department);
        this.branchOffice = new BranchOffice(branchOffice);
        this.currentTask = currentTask;
        this.eventEventFactoryIF = eventEventFactoryIF;
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

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = EmployeeStatus.valueOf(employeeStatus);
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

    public EventFactoryIF<TimekeepingEvent> getEventEventFactoryIF() {
        return eventEventFactoryIF;
    }

    public void setEventEventFactoryIF(EventFactoryIF<TimekeepingEvent> eventEventFactoryIF) {
        this.eventEventFactoryIF = eventEventFactoryIF;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }

    public void setCalendarEvent(CalendarEvent calendarEvent) {
        this.calendarEvent = calendarEvent;
    }
}
