package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class StartBreak implements TimekeepingEventIF {

    private String time;

    public StartBreak(String time) {
        this.time = time;
    }
}
