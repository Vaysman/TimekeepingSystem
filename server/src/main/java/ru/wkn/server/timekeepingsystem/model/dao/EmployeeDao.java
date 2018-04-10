package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;

import java.util.List;

public class EmployeeDao implements Dao<Employee> {

    private JdbcEmployeeDao jdbcEmployeeDao;

    public EmployeeDao(JdbcEmployeeDao jdbcEmployeeDao) {
        this.jdbcEmployeeDao = jdbcEmployeeDao;
    }

    @Override
    public Employee create(Employee newInstance) throws PersistentException {
        jdbcEmployeeDao.create(newInstance);
        return newInstance;
    }

    @Override
    public Employee read(int id) throws PersistentException {
        return jdbcEmployeeDao.read(id);
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        jdbcEmployeeDao.update(transientObject);
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        jdbcEmployeeDao.delete(persistentObject);
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        return jdbcEmployeeDao.getAll();
    }
}
