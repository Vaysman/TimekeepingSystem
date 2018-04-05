package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event;

import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.Break;
import ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.Shift;

public class EventFactory implements EventFactoryIF<TimekeepingEvent> {

    @Override
    public TimekeepingEvent createTimekeepingEvent(String type, String startTime) {
        return (type.equals("shift")) ? new Shift(startTime) : (type.equals("break")) ? new Break(startTime) : null;
    }
}
