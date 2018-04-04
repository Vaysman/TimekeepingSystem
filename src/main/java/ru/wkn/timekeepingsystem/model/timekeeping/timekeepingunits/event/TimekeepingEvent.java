package ru.wkn.timekeepingsystem.model.timekeeping.timekeepingunits.event;

public abstract class TimekeepingEvent {

    private String startTime;
    private String endTime;

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
