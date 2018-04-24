package ru.wkn.server.model.timekeeping.managers;

import ru.wkn.server.model.dao.Dao;
import ru.wkn.server.model.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import java.util.List;

public class TimekeepingEventManager {

    private Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> eventDao;
    private EventFactoryIF<TimekeepingEvent> eventFactoryIF;

    public TimekeepingEventManager(Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> eventDao, EventFactoryIF<TimekeepingEvent> eventEventFactoryIF) {
        this.eventDao = eventDao;
        this.eventFactoryIF = eventEventFactoryIF;
    }

    public TimekeepingEvent createEvent(int eventID, int employeeID, String type, String time, String date) {
        TimekeepingEvent event = null;
        try {
            event = eventDao.create(eventFactoryIF.createTimekeepingEvent(eventID, employeeID, type, time, date));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return event;
    }

    public Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> getEventDao() {
        return eventDao;
    }
}
