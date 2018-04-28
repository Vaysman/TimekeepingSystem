package ru.wkn.server.pages.container;

import ru.wkn.server.model.branchoffice.department.employee.Employee;

import java.util.StringJoiner;

public class Container {

    public static String readEmployeeInformation(Employee employee) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(employee.getName());
        stringJoiner.add(employee.getSurname());
        stringJoiner.add(employee.getTelephoneNumber());
        stringJoiner.add(employee.getEmployeeStatusEnum().toString());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getLogin());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getPassword());
        stringJoiner.add(employee.getDepartment().getDepartmentName());
        stringJoiner.add(employee.getDepartment().getBranchOffice().getBranchOfficeName());
        return stringJoiner.toString();
    }
}
