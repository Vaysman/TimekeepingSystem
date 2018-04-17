package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactory;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao implements Dao<TimekeepingEvent> {

    private DataSource dataSource;

    public JdbcEventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql, int index, Integer currentId) throws PersistentException, SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(index, currentId);
        return statement;
    }

    private void setStatements(PreparedStatement statement, TimekeepingEvent currentInstance) throws SQLException {
        statement.setInt(1, currentInstance.getEmployeeID());
        statement.setString(2, currentInstance.getType());
        statement.setString(3, currentInstance.getTime());
        statement.setString(4, currentInstance.getDate());
    }

    private TimekeepingEvent getTimekeepingEvent(ResultSet set, EventFactoryIF eventFactoryIF) throws SQLException {
        Integer employeeId = set.getInt(1);
        String type = set.getString(2);
        String time = set.getString(3);
        String date = set.getString(4);
        return (TimekeepingEvent) eventFactoryIF.createTimekeepingEvent(employeeId, type, time, date);
    }

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        String sql = "INSERT INTO app.timekeeping_event (employee_id, type, time, date) VALUES (?,?,?,?);";
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
    public TimekeepingEvent read(int id) throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_event WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, id)) {
            ResultSet set = statement.executeQuery();
            set.next();
            return getTimekeepingEvent(set, new EventFactory());
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        String sql = "UPDATE app.timekeeping_event SET type = ?, time = ?, date = ? WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, transientObject.getEmployeeID())) {
            setStatements(statement, transientObject);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        String sql = "DELETE FROM app.timekeeping_event WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, persistentObject.getEmployeeID())) {
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_event;";
        List<TimekeepingEvent> events = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                TimekeepingEvent event = getTimekeepingEvent(set, new EventFactory());
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
