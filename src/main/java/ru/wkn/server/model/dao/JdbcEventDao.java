package ru.wkn.server.model.dao;

import ru.wkn.server.model.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactory;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao implements Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> {

    private DataSource dataSource;

    public JdbcEventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql, int index, Integer currentId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(index, currentId);
        return statement;
    }

    private void setStatements(PreparedStatement statement, TimekeepingEvent currentInstance) throws SQLException {
        statement.setInt(1, currentInstance.getEventID());
        statement.setInt(2, currentInstance.getEmployeeID());
        statement.setString(3, currentInstance.getType());
        statement.setString(4, currentInstance.getTime());
        statement.setString(5, currentInstance.getDate());
    }

    private TimekeepingEvent getTimekeepingEvent(ResultSet set, EventFactoryIF<TimekeepingEvent> eventFactoryIF) throws SQLException {
        Integer eventID = set.getInt(1);
        Integer employeeId = set.getInt(2);
        String type = set.getString(3);
        String time = set.getString(4);
        String date = set.getString(5);
        return eventFactoryIF.createTimekeepingEvent(eventID, employeeId, type, time, date);
    }

    private List<TimekeepingEvent> getTimekeepingEvents(Statement statement, String sql, EventFactoryIF<TimekeepingEvent> eventFactoryIF) throws SQLException {
        List<TimekeepingEvent> events = new ArrayList<>();
        ResultSet set = statement.executeQuery(sql);
        while (set.next()) {
            events.add(getTimekeepingEvent(set, eventFactoryIF));
        }
        return events;
    }

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        String sql = "INSERT INTO app.timekeeping_events (employee_id, type_event, time_event, date_event) VALUES (?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setStatements(statement, newInstance);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            int generatedId = generatedKeys.getInt(1);
            newInstance.setEventID(generatedId);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
        return newInstance;
    }

    @Override
    public List<TimekeepingEvent> read(Integer employeeId) throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_events WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = getPreparedStatement(connection, sql, 1, employeeId)) {
            return getTimekeepingEvents(statement, sql, new EventFactory());
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        String sql = "UPDATE app.timekeeping_events SET event_id = ?, type_event = ?, time_event = ?, date_event = ? WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 5, transientObject.getEmployeeID())) {
            setStatements(statement, transientObject);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        String sql = "DELETE FROM app.timekeeping_events WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, persistentObject.getEmployeeID())) {
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_events;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            return getTimekeepingEvents(statement, sql, new EventFactory());
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
