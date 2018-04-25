package ru.wkn.server.model.branchoffice;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "employees")
public class BranchOffice {

    @Column(name = "branch_office")
    private String branchOfficeName;

    public BranchOffice(String branchOfficeName) {
        this.branchOfficeName = branchOfficeName;
    }

    public String getBranchOfficeName() {
        return branchOfficeName;
    }

    @Override
    public String toString() {
        return "BranchOffice{" +
                "branchOfficeName='" + branchOfficeName + '\'' +
                '}';
    }
}
