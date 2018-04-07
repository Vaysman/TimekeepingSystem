package ru.wkn.timekeepingsystem.model.dao;

import ru.wkn.timekeepingsystem.model.branchoffice.department.employee.Employee;
import ru.wkn.timekeepingsystem.model.dao.persistent.PersistentException;

import java.util.List;

public class EmployeeDao implements Dao<Employee> {

    private JdbcForEmployeeDao jdbcForEmployeeDao;

    public EmployeeDao(JdbcForEmployeeDao jdbcForEmployeeDao) {
        this.jdbcForEmployeeDao = jdbcForEmployeeDao;
    }

    @Override
    public Employee create(Employee newInstance) throws PersistentException {
        return newInstance;
    }

    @Override
    public Employee read(int id) throws PersistentException {
        return getAll().get(id - 1);
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        if ((transientObject.getEmployeeID() <= getAll().size()) && (transientObject.getEmployeeID() - 1 > 0)) {
            getAll().get(transientObject.getEmployeeID() - 1).setName(transientObject.getName());
            getAll().get(transientObject.getEmployeeID() - 1).setSurname(transientObject.getSurname());
            getAll().get(transientObject.getEmployeeID() - 1).setTelephoneNumber(transientObject.getTelephoneNumber());
            getAll().get(transientObject.getEmployeeID() - 1).setEmployeeStatus(transientObject.getEmployeeStatus().toString());
            getAll().get(transientObject.getEmployeeID() - 1).setLogin(transientObject.getLogin());
            getAll().get(transientObject.getEmployeeID() - 1).setPassword(transientObject.getPassword());
            getAll().get(transientObject.getEmployeeID() - 1).setDepartment(transientObject.getDepartment());
            getAll().get(transientObject.getEmployeeID() - 1).setBranchOffice(transientObject.getBranchOffice());
            getAll().get(transientObject.getEmployeeID() - 1).setCurrentTask(transientObject.getCurrentTask());
            getAll().get(transientObject.getEmployeeID() - 1).setEventEventFactoryIF(transientObject.getEventEventFactoryIF());
        }
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        getAll().remove(persistentObject.getEmployeeID() - 1);
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        return jdbcForEmployeeDao.getAll();
    }
}
