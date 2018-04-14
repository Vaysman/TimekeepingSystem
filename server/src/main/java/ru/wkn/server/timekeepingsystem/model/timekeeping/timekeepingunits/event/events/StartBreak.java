package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEventIF;

public class StartBreak implements TimekeepingEventIF {

    private String time;
    private String date;

    public StartBreak(String time, String date) {
        this.time = time;
        this.date = date;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getDate() {
        return date;
    }
}
