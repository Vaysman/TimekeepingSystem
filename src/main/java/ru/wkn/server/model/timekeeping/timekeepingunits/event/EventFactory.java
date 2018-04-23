package ru.wkn.server.model.timekeeping.timekeepingunits.event;

import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.EndBreak;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.EndShift;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.StartBreak;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.events.StartShift;

public class EventFactory implements EventFactoryIF<TimekeepingEvent> {

    @Override
    public TimekeepingEvent createTimekeepingEvent(int eventID, int employeeID, String type, String time, String date) {
        return (type.equals("start_shift")) ? new StartShift(eventID, employeeID, type, time, date)
                : (type.equals("start_break")) ? new StartBreak(eventID, employeeID, type, time, date)
                : (type.equals("end_shift")) ? new EndShift(eventID, employeeID, type, time, date)
                : (type.equals("end_break")) ? new EndBreak(eventID, employeeID, type, time, date)
                : null;
    }
}
