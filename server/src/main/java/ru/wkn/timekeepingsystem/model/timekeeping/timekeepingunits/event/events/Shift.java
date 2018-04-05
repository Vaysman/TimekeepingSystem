package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class Shift extends TimekeepingEvent {

    public Shift(String startTime) {
        super(startTime);
    }
}
