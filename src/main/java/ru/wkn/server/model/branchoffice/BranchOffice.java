package ru.wkn.server.model.branchoffice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
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
}
