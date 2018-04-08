package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class EndBreak extends TimekeepingEvent {

    public EndBreak(String time) {
        super(time);
    }
}
