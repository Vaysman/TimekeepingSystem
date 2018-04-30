package ru.wkn.server.model.timekeeping.managers;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.branchoffice.department.employee.status.EmployeeStatusEnum;
import ru.wkn.server.model.datasource.dao.Dao;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.util.List;

public class EmployeeManager {

    private Dao<Employee, Employee, Integer> employeeDao;

    public EmployeeManager(Dao<Employee, Employee, Integer> employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void createEmployee(String name, String surname, String telephoneNumber, String employeeStatus, String login, String password, String department, String branchOffice, TimekeepingEventManager timekeepingEventManager) throws PersistentException {
        employeeDao.create(new Employee(name, surname, telephoneNumber, EmployeeStatusEnum.valueOf(employeeStatus), login, password, department, branchOffice));
    }

    public Employee readEmployee(int id) throws PersistentException {
        return employeeDao.getAll().get(id);
    }

    public void updateEmployee(Employee transientEmployee) throws PersistentException {
        employeeDao.update(transientEmployee);
    }

    public void deleteEmployee(Employee persistentEmployee) throws PersistentException {
        employeeDao.delete(persistentEmployee);
        for (int i = persistentEmployee.getEmployeeID(); i < employeeDao.getAll().size() - 1; i++) {
            employeeDao.update(employeeDao.read(i + 1));
        }
        employeeDao.delete(employeeDao.read(employeeDao.getAll().size() - 1));
    }

    public void deleteAll() throws PersistentException {
        for (int i = 0; i < employeeDao.getAll().size(); i++) {
            employeeDao.delete(employeeDao.read(i));
        }
    }

    public List<Employee> getAll() throws PersistentException {
        return employeeDao.getAll();
    }
}
