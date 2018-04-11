package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class EndShift implements TimekeepingEventIF {

    private String time;

    public EndShift(String time) {
        this.time = time;
    }
}
