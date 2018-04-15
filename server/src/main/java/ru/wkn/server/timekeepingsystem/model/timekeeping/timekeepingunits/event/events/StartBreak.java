package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

public class StartBreak extends TimekeepingEvent {

    private String time;
    private String date;
    private String nameOfCreator;
    private String surnameOfCreator;

    public StartBreak(String time, String date, String nameOfCreator, String surnameOfCreator) {
        super(time, date, nameOfCreator, surnameOfCreator);
        this.time = time;
        this.date = date;
        this.nameOfCreator = nameOfCreator;
        this.surnameOfCreator = surnameOfCreator;
    }
}
