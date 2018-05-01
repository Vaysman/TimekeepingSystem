package ru.wkn.server.model.datasource.dao;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.util.List;

public class EmployeeDao implements Dao<Employee, Employee, Integer> {

    @Override
    public Employee create(Employee newInstance) throws PersistentException {
        return (Employee) new DaoTool(Employee.class).createObject(newInstance);
    }

    @Override
    public Employee read(Integer id) throws PersistentException {
        return new DaoTool<>(Employee.class).read(id);
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        new DaoTool<>(Employee.class).update(transientObject);
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        new DaoTool<>(Employee.class).delete(persistentObject);
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        return new DaoTool<>(Employee.class).getAll();
    }
}
