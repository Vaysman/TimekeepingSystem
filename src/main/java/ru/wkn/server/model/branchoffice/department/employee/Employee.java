package ru.wkn.server.model.branchoffice.department.employee;

import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.timekeeping.managers.TimekeepingEventManager;
import ru.wkn.server.model.branchoffice.BranchOffice;
import ru.wkn.server.model.branchoffice.department.Department;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "department")
    private String department;
    @Column(name = "branch_office")
    private String branchOffice;

    public Employee() {}

    public Employee(String name, String surname, String telephoneNumber, EmployeeStatusEnum employeeStatusEnum, String login, String password, String department, String branchOffice) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.employeeStatusEnum = employeeStatusEnum;
        this.login = login;
        this.password = password;
        this.department = department;
        this.branchOffice = branchOffice;
    }

    public Employee(int employeeID, String name, String surname, String telephoneNumber, String employeeStatus, String login, String password, String department, String branchOffice) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.employeeStatusEnum = employeeStatusEnum;
        this.login = login;
        this.password = password;
        this.department = department;
        this.branchOffice = branchOffice;
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
        return new EmployeeAuthorizationData(login, password);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return new Department(department, new BranchOffice(branchOffice));
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }
}
