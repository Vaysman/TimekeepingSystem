package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class EndBreak implements TimekeepingEventIF {

    private String time;

    public EndBreak(String time) {
        this.time = time;
    }
}
