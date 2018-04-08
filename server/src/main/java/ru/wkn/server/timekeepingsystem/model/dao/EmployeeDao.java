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
        return newInstance;
    }

    @Override
    public Employee read(int id) throws PersistentException {
        return getAll().get(id);
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        if ((transientObject.getEmployeeID() < getAll().size()) && (transientObject.getEmployeeID() >= 0)) {
            getAll().get(transientObject.getEmployeeID()).setName(transientObject.getName());
            getAll().get(transientObject.getEmployeeID()).setSurname(transientObject.getSurname());
            getAll().get(transientObject.getEmployeeID()).setTelephoneNumber(transientObject.getTelephoneNumber());
            getAll().get(transientObject.getEmployeeID()).setEmployeeStatus(transientObject.getEmployeeStatus().toString());
            getAll().get(transientObject.getEmployeeID()).getEmployeeAuthorizationData().setLogin(transientObject.getEmployeeAuthorizationData().getLogin());
            getAll().get(transientObject.getEmployeeID()).getEmployeeAuthorizationData().setPassword(transientObject.getEmployeeAuthorizationData().getPassword());
            getAll().get(transientObject.getEmployeeID()).setDepartment(transientObject.getDepartment());
            getAll().get(transientObject.getEmployeeID()).setBranchOffice(transientObject.getBranchOffice());
            getAll().get(transientObject.getEmployeeID()).setCurrentTask(transientObject.getCurrentTask());
            getAll().get(transientObject.getEmployeeID()).setEventEventFactoryIF(transientObject.getEventEventFactoryIF());
            getAll().get(transientObject.getEmployeeID()).setCalendarEvent(transientObject.getCalendarEvent());
        }
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        getAll().remove(persistentObject.getEmployeeID());
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        return jdbcEmployeeDao.getAll();
    }
}
