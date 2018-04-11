package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class TimekeepingEventManager {

    private Dao<TimekeepingEventIF> eventDao;
    private EventFactoryIF<TimekeepingEventIF> eventFactoryIF;

    public TimekeepingEventManager(Dao<TimekeepingEventIF> eventDao, EventFactoryIF<TimekeepingEventIF> eventEventFactoryIF) {
        this.eventDao = eventDao;
        this.eventFactoryIF = eventEventFactoryIF;
    }
}
