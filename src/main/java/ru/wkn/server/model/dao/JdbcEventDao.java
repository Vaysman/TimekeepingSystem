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

    private TimekeepingEvent getTimekeepingEvent(ResultSet set, EventFactoryIF<TimekeepingEvent> eventFactoryIF) throws SQLException {
        Integer employeeId = set.getInt(1);
        String type = set.getString(2);
        String time = set.getString(3);
        String date = set.getString(4);
        return eventFactoryIF.createTimekeepingEvent(employeeId, type, time, date);
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
    public synchronized TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        String sql = "INSERT INTO app.timekeeping_events (employee_id, type_event, time_event, date_event) VALUES (?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setStatements(statement, newInstance);
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
        return newInstance;
    }

    @Override
    public synchronized List<TimekeepingEvent> read(Integer id) throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_events WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = getPreparedStatement(connection, sql, 1, id)) {
            return getTimekeepingEvents(statement, sql, new EventFactory());
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized void update(TimekeepingEvent transientObject) throws PersistentException {
        String sql = "UPDATE app.timekeeping_events SET type_event = ?, time_event = ?, date_event = ? WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 4, transientObject.getEmployeeID())) {
            setStatements(statement, transientObject);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized void delete(TimekeepingEvent persistentObject) throws PersistentException {
        String sql = "DELETE FROM app.timekeeping_events WHERE employee_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getPreparedStatement(connection, sql, 1, persistentObject.getEmployeeID())) {
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public synchronized List<TimekeepingEvent> getAll() throws PersistentException {
        String sql = "SELECT * FROM app.timekeeping_events;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            return getTimekeepingEvents(statement, sql, new EventFactory());
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
