package ru.wkn.server.view;

public class EmployeeWindow extends Window {

    public EmployeeWindow(String uniqueMessage) {
        super(uniqueMessage);
    }

    public String getUniqueMessage() {
        return super.getBaseMessage() + "\n" + super.getUniqueMessage() + "\n";
    }
}
