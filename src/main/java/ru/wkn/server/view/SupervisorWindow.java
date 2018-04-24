package ru.wkn.server.view;

public class SupervisorWindow extends Window {

    public SupervisorWindow(String uniqueMessage) {
        super(uniqueMessage);
    }

    public String getUniqueMessage() {
        return super.getBaseMessage() + "\n" + super.getUniqueMessage() + "\n";
    }
}
