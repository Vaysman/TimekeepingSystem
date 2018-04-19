package ru.wkn.server.model.dao;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.dao.persistent.PersistentException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements Dao<Employee, Employee, Integer> {

    private DataSource dataSource;

    public JdbcEmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql, int index, Integer currentId) throws PersistentException, SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(index, currentId);
        return statement;
    }

    private void setStatements(PreparedStatement statement, Employee currentInstance) throws SQLException {
        statement.setString(1, currentInstance.getName());
        statement.setString(2, currentInstance.getSurname());
        statement.setString(3, currentInstance.getTelephoneNumber());
        statement.setString(4, currentInstance.getEmployeeStatusEnum().toString());
        statement.setString(5, currentInstance.getEmployeeAuthorizationData().getLogin());
        statement.setString(6, currentInstance.getEmployeeAuthorizationData().getPassword());
        statement.setString(7, currentInstance.getDepartment().getDepartmentName());
        statement.setString(8, currentInstance.getDepartment().getBranchOffice().getBranchOfficeName());
    }

    private Employee getEntry(ResultSet set) throws SQLException {
        Integer employeeID = set.getInt(1);
        String name = set.getString(2);
        String surname = set.getString(3);
        String telephoneNumber = set.getString(4);
        String employeeStatus = set.getString(5);
        String login = set.getString(6);
        String password = set.getString(7);
        String department = set.getString(8);
        String branchOffice = set.getString(9);
        return new Employee(employeeID, name, surname, telephoneNumber, employeeStatus, login, password, department, branchOffice, null, null);
    }

    @Override
    public Employee create(Employee newInstance) throws PersistentException {
        String sql = "INSERT INTO app.employees (name, surname, telephone_number, employee_status, login, password, department, branch_office) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setStatements(statement, newInstance);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            int generatedId = generatedKeys.getInt(1);
            newInstance.setEmployeeID(generatedId);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
        return newInstance;
    }

    @Override
    public Employee read(Integer id) throws PersistentException {
        String sql = "SELECT * FROM app.employees WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, id)) {
            ResultSet set = statement.executeQuery();
            set.next();
            return getEntry(set);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Employee transientObject) throws PersistentException {
        String sql = "UPDATE app.employees SET name = ?, surname = ?, telephone_number = ?, employee_status = ?, login = ?, password = ?, department = ?, branch_office = ?  WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 9, transientObject.getEmployeeID())) {
            setStatements(statement, transientObject);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Employee persistentObject) throws PersistentException {
        String sql = "DELETE FROM app.employees WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, persistentObject.getEmployeeID())) {
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Employee> getAll() throws PersistentException {
        String sql = "SELECT * FROM app.employees;";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                Employee employee = getEntry(set);
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}