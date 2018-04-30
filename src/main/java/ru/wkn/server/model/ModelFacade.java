package ru.wkn.server.model;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.branchoffice.department.employee.Supervisor;
import ru.wkn.server.model.branchoffice.department.employee.Timekeeper;
import ru.wkn.server.model.datasource.dao.*;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.managers.EmployeeManager;
import ru.wkn.server.model.timekeeping.managers.TaskManager;
import ru.wkn.server.model.timekeeping.managers.TimekeepingEventManager;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.summary.TimekeepingReport;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactory;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class ModelFacade {

    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Searcher searcher;

    private EmployeeManager employeeManager;
    private TaskManager taskManager;
    private TimekeepingEventManager timekeepingEventManager;
    private DayManager dayManager;

    private TimekeepingReport timekeepingReport;

    public ModelFacade(EmployeeAuthorizationData employeeAuthorizationData) {
        Dao<Employee, Employee, Integer> employeeDao = new EmployeeDao();
        Dao<Task, List<Task>, Integer> taskDao = new TaskDao();
        Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> timekeepingEventDao = new EventDao();

        searcher = new Searcher(timekeepingEventDao, taskDao, employeeDao);

        eventEventFactoryIF = new EventFactory();

        this.employeeAuthorizationData = employeeAuthorizationData;

        employeeManager = new EmployeeManager(employeeDao);
        taskManager = new TaskManager(taskDao);
        timekeepingEventManager = new TimekeepingEventManager(timekeepingEventDao, eventEventFactoryIF);
        dayManager = new DayManager(employeeDao, taskDao, timekeepingEventDao);

        timekeepingReport = new TimekeepingReport(dayManager);
    }

    public Employee getEmployee() {
        return searcher.getEmployeeByEmployeeAuthorizationData(employeeAuthorizationData);
    }

    public Supervisor getSupervisor() {
        return new Supervisor(getEmployee(), employeeManager, timekeepingReport);
    }

    public Timekeeper getTimekeeper() {
        return new Timekeeper(getEmployee(), taskManager);
    }

    public Searcher getSearcher() {
        return searcher;
    }
}
