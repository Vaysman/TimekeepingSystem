package ru.wkn.server.model.datasource.dao;

import ru.wkn.server.model.datasource.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import java.util.List;

public class EventDao implements Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> {

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        return (TimekeepingEvent) new DaoTool().createObject(newInstance);
    }

    @Override
    public List<TimekeepingEvent> read(Integer id) throws PersistentException {
        return new DaoTool<TimekeepingEvent>().read("from app.timekeeping_events where id = :employeeID", "employeeID", id);
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        new DaoTool<>().update(transientObject);
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        new DaoTool<>().delete(persistentObject);
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        return new DaoTool<TimekeepingEvent>().getAll();
    }
}
