package ru.wkn.server.model.timekeeping.timekeepingunits.event;

import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.EndBreak;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.EndShift;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.StartBreak;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.StartShift;

public class EventFactory implements EventFactoryIF<TimekeepingEvent> {

    @Override
    public TimekeepingEvent createTimekeepingEvent(int employeeID, String type, String time, String date) {
        return (type.equals("start_shift")) ? new StartShift(employeeID, type, time, date)
                : (type.equals("start_break")) ? new StartBreak(employeeID, type, time, date)
                : (type.equals("end_shift")) ? new EndShift(employeeID, type, time, date)
                : (type.equals("end_break")) ? new EndBreak(employeeID, type, time, date)
                : null;
    }
}
