package ru.wkn.server.model.datasource.dao;

import ru.wkn.server.model.datasource.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class TaskDao implements Dao<Task, List<Task>, Integer> {

    @Override
    public Task create(Task newInstance) throws PersistentException {
        return (Task) new DaoTool<>().createObject(newInstance);
    }

    @Override
    public List<Task> read(Integer id) throws PersistentException {
        return new DaoTool<Task>().read("from tasks where id = :employeeID", "employeeID", id);
    }

    @Override
    public void update(Task transientObject) throws PersistentException {
        new DaoTool<>().update(transientObject);
    }

    @Override
    public void delete(Task persistentObject) throws PersistentException {
        new DaoTool<>().delete(persistentObject);
    }

    @Override
    public List<Task> getAll() throws PersistentException {
        return new DaoTool<Task>().getAll();
    }
}
