package ru.wkn.server.model.branchoffice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class BranchOffice {

    private String branchOfficeName;

    public BranchOffice(String branchOfficeName) {
        this.branchOfficeName = branchOfficeName;
    }

    @Column(name = "branch_office")
    public String getBranchOfficeName() {
        return branchOfficeName;
    }
}
