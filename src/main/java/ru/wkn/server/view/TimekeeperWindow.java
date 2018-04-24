package ru.wkn.server.view;

public class TimekeeperWindow extends Window {

    public TimekeeperWindow(String uniqueMessage) {
        super(uniqueMessage);
    }

    public String getUniqueMessage() {
        return super.getBaseMessage() + "\n" + super.getUniqueMessage() + "\n";
    }
}
