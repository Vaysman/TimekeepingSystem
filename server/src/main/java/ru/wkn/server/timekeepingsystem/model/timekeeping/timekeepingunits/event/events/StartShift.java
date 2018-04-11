package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class StartShift implements TimekeepingEventIF {

    private String time;

    public StartShift(String time) {
        this.time = time;
    }
}
