package ru.wkn.server.model.branchoffice;

public class BranchOffice {

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
