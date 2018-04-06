package ru.wkn.timekeepingsystem.model.timekeeping.log.persistent;

import ru.wkn.timekeepingsystem.model.branchoffice.department.employee.Employee;

import java.util.List;

public class JdbcForEmployeeDao implements Dao<Integer, Employee> {

    @Override
    public Employee create(Employee newInstance) throws PersistentException {
        return null;
    }

    @Override
    public Employee read(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        return null;
    }
}
