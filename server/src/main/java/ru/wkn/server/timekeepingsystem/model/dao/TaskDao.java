package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import java.util.List;

public class TaskDao implements Dao<Task, List<Task>, Integer> {

    private JdbcTaskDao jdbcTaskDao;

    public TaskDao(JdbcTaskDao jdbcTaskDao) {
        this.jdbcTaskDao = jdbcTaskDao;
    }

    @Override
    public Task create(Task newInstance) throws PersistentException {
        return jdbcTaskDao.create(newInstance);
    }

    @Override
    public List<Task> read(Integer id) throws PersistentException {
        return jdbcTaskDao.read(id);
    }

    @Override
    public void update(Task transientObject) throws PersistentException {
        jdbcTaskDao.update(transientObject);
    }

    @Override
    public void delete(Task persistentObject) throws PersistentException {
        jdbcTaskDao.delete(persistentObject);
    }

    @Override
    public List<Task> getAll() throws PersistentException {
        return jdbcTaskDao.getAll();
    }
}
