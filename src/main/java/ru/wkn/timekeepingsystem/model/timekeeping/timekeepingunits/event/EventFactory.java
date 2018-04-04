package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.Break;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.Shift;

public class EventFactory implements EventFactoryIF<TimekeepingEvent> {

    @Override
    public TimekeepingEvent createTimekeepingEvent(String type, String time) {
        if (type.equals("shift")) {
            return new Shift();
        }
        if (type.equals("break")) {
            return new Break();
        }
        else {
            return null;
        }
    }
}
