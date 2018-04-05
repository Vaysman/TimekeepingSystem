package ru.wkn.timekeepingsystem.model.timekeeping.data;

import java.util.Objects;

public class EmployeeAuthorizationData {

    private String employeeID;
    private String password;

    public EmployeeAuthorizationData(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAuthorizationData that = (EmployeeAuthorizationData) o;
        return Objects.equals(employeeID, that.employeeID) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, password);
    }

    @Override
    public String toString() {
        return "EmployeeAuthorizationData{" +
                "employeeID='" + employeeID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
