package ru.wkn.server.model.timekeeping.timekeepingunits.event;

public interface EventFactoryIF<T> {

    T createTimekeepingEvent(int eventID, int employeeID, String type, String time, String date);
}
