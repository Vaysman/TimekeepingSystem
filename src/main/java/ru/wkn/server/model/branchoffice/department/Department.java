package ru.wkn.server.model.branchoffice.department;

import ru.wkn.server.model.branchoffice.BranchOffice;

public class Department {

    private String departmentName;
    private BranchOffice branchOffice;

    public Department(String departmentName, BranchOffice branchOffice) {
        this.departmentName = departmentName;
        this.branchOffice = branchOffice;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", branchOffice=" + branchOffice +
                '}';
    }
}
