package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public interface EventFactoryIF<T> {

    T createTimekeepingEvent(String type, String time);
}
