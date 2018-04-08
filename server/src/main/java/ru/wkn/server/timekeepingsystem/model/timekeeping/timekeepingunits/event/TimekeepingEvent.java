package ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public abstract class TimekeepingEvent {

    private String startTime;
    private String endTime;

    public TimekeepingEvent(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
