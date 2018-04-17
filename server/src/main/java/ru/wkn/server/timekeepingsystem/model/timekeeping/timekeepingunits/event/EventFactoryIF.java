package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public interface EventFactoryIF<T> {

    T createTimekeepingEvent(int employeeID, String type, String time, String date);
}
