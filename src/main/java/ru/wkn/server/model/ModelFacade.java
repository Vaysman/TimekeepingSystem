package ru.wkn.server.model;

import ru.wkn.server.model.branchoffice.department.employee.EmployeeCreator;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class ModelFacade extends Thread {

    private EmployeeCreator employeeCreator;
    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Searcher searcher;

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(1);
            //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
