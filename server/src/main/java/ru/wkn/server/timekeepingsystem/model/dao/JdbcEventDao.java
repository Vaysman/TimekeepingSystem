package ru.wkn.server.timekeepingsystem.model.dao;

import ru.wkn.server.timekeepingsystem.model.dao.persistent.PersistentException;
import ru.wkn.server.timekeepingsystem.model.timekeeping.timekeepingunits.event.TimekeepingEvent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcEventDao implements Dao<TimekeepingEvent> {

    private DataSource dataSource;

    public JdbcEventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void setStatements(PreparedStatement statement, TimekeepingEvent currentInstance) throws SQLException {
        statement.setInt(1, currentInstance.getEmployeeID());
        statement.setString(2, currentInstance.getTime());
        statement.setString(3, currentInstance.getDate());
    }

    @Override
    public TimekeepingEvent create(TimekeepingEvent newInstance) throws PersistentException {
        String sql = "INSERT INTO app.timekeeping_event (employee_id, time, date) VALUES (?,?,?);";
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
        return null;
    }

    @Override
    public void update(TimekeepingEvent transientObject) throws PersistentException {
        //
    }

    @Override
    public void delete(TimekeepingEvent persistentObject) throws PersistentException {
        //
    }

    @Override
    public List<TimekeepingEvent> getAll() throws PersistentException {
        return null;
    }
}
