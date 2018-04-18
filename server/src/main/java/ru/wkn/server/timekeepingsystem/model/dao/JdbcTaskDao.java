package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.task.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTaskDao implements Dao<Task, List<Task>, Integer> {

    private DataSource dataSource;

    public JdbcTaskDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql, int index, Integer currentId) throws PersistentException, SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(index, currentId);
        return statement;
    }

    private void setStatements(PreparedStatement statement, Task currentInstance) throws SQLException {
        statement.setInt(1, currentInstance.getEmployeeID());
        statement.setString(2, currentInstance.getDefinition());
        statement.setString(3, currentInstance.getStartTime());
        statement.setString(4, currentInstance.getEndTime());
        statement.setString(5, currentInstance.getDate());
    }

    private Task getTask(ResultSet set) throws SQLException {
        Integer id = set.getInt(1);
        String definition = set.getString(2);
        String startTime = set.getString(3);
        String endTime = set.getString(4);
        String date = set.getString(5);
        boolean isAccomplished = Boolean.getBoolean(set.getString(6));
        return new Task(id, definition, startTime, endTime, date, isAccomplished);
    }

    private List<Task> getTasks(Statement statement, String sql) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        ResultSet set = statement.executeQuery(sql);
        while (set.next()) {
            tasks.add(getTask(set));
        }
        return tasks;
    }

    @Override
    public synchronized Task create(Task newInstance) throws PersistentException {
        String sql = "INSERT INTO app.tasks (employee_id, definition, start_time, end_time, date_task, status_task) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setStatements(statement, newInstance);
            statement.execute();
            return newInstance;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized List<Task> read(Integer id) throws PersistentException {
        String sql = "SELECT * FROM app.tasks WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = getPreparedStatement(connection, sql, 1, id)) {
            return getTasks(statement, sql);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized void update(Task transientObject) throws PersistentException {
        String sql = "UPDATE app.tasks SET definition = ?, start_time = ?, end_time = ?, date_task = ?, status_task = ? WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 6, transientObject.getEmployeeID())) {
            setStatements(statement, transientObject);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized void delete(Task persistentObject) throws PersistentException {
        String sql = "DELETE FROM app.tasks WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, persistentObject.getEmployeeID())) {
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized List<Task> getAll() throws PersistentException {
        String sql = "SELECT * FROM app.tasks;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            return getTasks(statement, sql);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
