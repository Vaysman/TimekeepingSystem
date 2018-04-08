package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartShift extends TimekeepingEvent {

    public StartShift(String time) {
        super(time);
    }
}
