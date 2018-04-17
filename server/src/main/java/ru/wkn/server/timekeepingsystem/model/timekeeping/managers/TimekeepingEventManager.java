package ru.wkn.server.timekeepingsystem.model.timekeeping.managers;

import ru.wkn.server.timekeepingsystem.model.dao.Dao;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class TimekeepingEventManager {

    private Dao<TimekeepingEvent> eventDao;
    private EventFactoryIF<TimekeepingEvent> eventFactoryIF;

    public TimekeepingEventManager(Dao<TimekeepingEvent> eventDao, EventFactoryIF<TimekeepingEvent> eventEventFactoryIF) {
        this.eventDao = eventDao;
        this.eventFactoryIF = eventEventFactoryIF;
    }

    public TimekeepingEvent createEvent(int employeeID, String type, String time, String date) throws PersistentException {
        return eventDao.create(eventFactoryIF.createTimekeepingEvent(employeeID, type, time, date));
    }

    public Dao<TimekeepingEvent> getEventDao() {
        return eventDao;
    }
}
