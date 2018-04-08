package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.EndBreak;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.EndShift;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.StartShift;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.events.StartBreak;

public class EventFactory implements EventFactoryIF<TimekeepingEvent> {

    @Override
    public TimekeepingEvent createTimekeepingEvent(String type, String time) {
        return (type.equals("start_shift")) ? new StartShift(time)
                : (type.equals("start_break")) ? new StartBreak(time)
                : (type.equals("end_shift")) ? new EndShift(time)
                : (type.equals("end_break")) ? new EndBreak(time)
                : null;
    }
}
