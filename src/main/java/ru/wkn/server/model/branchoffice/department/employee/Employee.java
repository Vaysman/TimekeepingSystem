package ru.wkn.server.model.branchoffice.department.employee;

import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.timekeeping.managers.TimekeepingEventManager;
import ru.wkn.server.model.branchoffice.BranchOffice;
import ru.wkn.server.model.branchoffice.department.Department;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private int employeeID;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "employee_status")
    private EmployeeStatusEnum employeeStatusEnum;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Department department;
    private TimekeepingEventManager timekeepingEventManager;

    public Employee(int employeeID, String name, String surname, String telephoneNumber, String employeeStatus, String login, String password, String department, String branchOffice, TimekeepingEventManager timekeepingEventManager) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.employeeStatusEnum = EmployeeStatusEnum.valueOf(employeeStatus);
        this.employeeAuthorizationData = new EmployeeAuthorizationData(login, password);
        this.department = new Department(department, new BranchOffice(branchOffice));
        this.timekeepingEventManager = timekeepingEventManager;
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

    public TimekeepingEventManager getTimekeepingEventManager() {
        return timekeepingEventManager;
    }

    public void setTimekeepingEventManager(TimekeepingEventManager timekeepingEventManager) {
        this.timekeepingEventManager = timekeepingEventManager;
    }
}
