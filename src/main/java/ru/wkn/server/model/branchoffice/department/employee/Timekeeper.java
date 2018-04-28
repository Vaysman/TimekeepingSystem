package ru.wkn.server.model.branchoffice.department.employee;

import ru.wkn.server.model.timekeeping.managers.TaskManager;

public class Timekeeper {

    private Employee employee;
    private TaskManager taskManager;

    public Timekeeper(Employee employee, TaskManager taskManager) {
        this.employee = employee;
        this.taskManager = taskManager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
