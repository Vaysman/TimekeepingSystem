package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTaskDao implements Dao<Task> {

    private DataSource dataSource;

    public JdbcTaskDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Task create(Task newInstance) throws PersistentException {
        return null;
    }

    @Override
    public Task read(int id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Task transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(Task persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<Task> getAll() throws PersistentException {
        return null;
    }
}
