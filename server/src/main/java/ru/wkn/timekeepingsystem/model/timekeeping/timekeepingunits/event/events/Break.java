package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class Break extends TimekeepingEvent {

    public Break(String startTime) {
        super(startTime);
    }
}
