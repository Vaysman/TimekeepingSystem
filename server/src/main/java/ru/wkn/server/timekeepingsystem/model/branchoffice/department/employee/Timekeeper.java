package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee;

import ru.wkn.server.timekeepingsystem.model.timekeeping.managers.DayManager;
import ru.wkn.server.timekeepingsystem.model.timekeeping.managers.TaskManager;
import ru.wkn.server.timekeepingsystem.model.timekeeping.summary.TimekeepingLog;

public class Timekeeper {

    private Employee employee;
    private DayManager dayManager;
    private TaskManager taskManager;
    private TimekeepingLog timekeepingLog;

    public Timekeeper(Employee employee, DayManager dayManager, TaskManager taskManager, TimekeepingLog timekeepingLog) {
        this.employee = employee;
        this.dayManager = dayManager;
        this.taskManager = taskManager;
        this.timekeepingLog = timekeepingLog;
    }

    public Employee getEmployee() {
        return employee;
    }

    public DayManager getDayManager() {
        return dayManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public TimekeepingLog getTimekeepingLog() {
        return timekeepingLog;
    }
}
