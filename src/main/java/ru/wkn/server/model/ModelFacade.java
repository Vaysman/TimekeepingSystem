package ru.wkn.server.model;

import ru.wkn.server.model.branchoffice.department.employee.EmployeeCreator;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactory;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class ModelFacade {

    private EmployeeCreator employeeCreator;
    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Searcher searcher;

    public ModelFacade(EmployeeAuthorizationData employeeAuthorizationData, Searcher searcher) {
        this.employeeCreator = new EmployeeCreator(searcher);
        this.eventEventFactoryIF = new EventFactory();
        this.employeeAuthorizationData = employeeAuthorizationData;
        this.searcher = searcher;
    }
}
