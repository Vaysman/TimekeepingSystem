package ru.wkn.server.model.timekeeping.timekeepingunits.event;

import javax.persistence.*;

@Entity
@Table(name = "timekeeping_events")
public abstract class TimekeepingEvent {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private int eventID;
    @Column(name = "employee_id")
    private int employeeID;
    @Column(name = "type_event")
    private String type;
    @Column(name = "time_event")
    private String time;
    @Column(name = "date_event")
    private String date;

    public TimekeepingEvent(int employeeID, String type, String time, String date) {
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public TimekeepingEvent(int eventID, int employeeID, String type, String time, String date) {
        this.eventID = eventID;
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
