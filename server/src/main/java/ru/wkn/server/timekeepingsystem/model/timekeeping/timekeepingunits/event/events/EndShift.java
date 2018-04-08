package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class EndShift extends TimekeepingEvent {

    public EndShift(String time) {
        super(time);
    }
}
