package ru.wkn.server.model.timekeeping.timekeepingunits.event;

import javax.persistence.*;

@Entity
@Table(name = "timekeeping_events")
public abstract class TimekeepingEvent {

    private int eventID;
    private int employeeID;
    private String type;
    private String time;
    private String date;

    public TimekeepingEvent(int eventID, int employeeID, String type, String time, String date) {
        this.eventID = eventID;
        this.employeeID = employeeID;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    @Column(name = "employee_id")
    public int getEmployeeID() {
        return employeeID;
    }

    @Column(name = "type_event")
    public String getType() {
        return type;
    }

    @Column(name = "time_event")
    public String getTime() {
        return time;
    }

    @Column(name = "date_event")
    public String getDate() {
        return date;
    }
}
