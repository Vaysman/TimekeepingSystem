package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartBreak extends TimekeepingEvent {

    public StartBreak(String time) {
        super(time);
    }
}
