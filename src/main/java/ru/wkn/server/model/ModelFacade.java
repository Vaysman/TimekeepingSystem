package ru.wkn.server.model;

import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.branchoffice.department.employee.EmployeeCreator;
import ru.wkn.server.model.branchoffice.department.employee.Supervisor;
import ru.wkn.server.model.branchoffice.department.employee.Timekeeper;
import ru.wkn.server.model.dao.*;
import ru.wkn.server.model.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;
import ru.wkn.server.model.timekeeping.managers.DayManager;
import ru.wkn.server.model.timekeeping.managers.EmployeeManager;
import ru.wkn.server.model.timekeeping.managers.TaskManager;
import ru.wkn.server.model.timekeeping.summary.Searcher;
import ru.wkn.server.model.timekeeping.summary.TimekeepingLog;
import ru.wkn.server.model.timekeeping.summary.TimekeepingReport;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactory;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.EventFactoryIF;
import ru.wkn.server.model.timekeeping.timekeepingunits.event.TimekeepingEvent;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class ModelFacade {

    private EmployeeCreator employeeCreator;
    private EventFactoryIF<TimekeepingEvent> eventEventFactoryIF;
    private EmployeeAuthorizationData employeeAuthorizationData;
    private Searcher searcher;

    private EmployeeManager employeeManager;
    private TaskManager taskManager;
    private DayManager dayManager;

    private TimekeepingReport timekeepingReport;
    private TimekeepingLog timekeepingLog;

    public ModelFacade(EmployeeAuthorizationData employeeAuthorizationData) {
        DataSource dataSource = null;
        try {
            dataSource = new DataSourceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Dao<Employee, Employee, Integer> jdbcEmployeeDao = new JdbcEmployeeDao(dataSource);
        Dao<Task, List<Task>, Integer> jdbcTaskDao = new JdbcTaskDao(dataSource);
        Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> jdbcEventDao = new JdbcEventDao(dataSource);

        searcher = new Searcher(jdbcEventDao, jdbcTaskDao, jdbcEmployeeDao);

        this.employeeCreator = new EmployeeCreator(searcher);
        this.eventEventFactoryIF = new EventFactory();
        this.employeeAuthorizationData = employeeAuthorizationData;

        Dao<Employee, Employee, Integer> employeeDao = new EmployeeDao((JdbcEmployeeDao) jdbcEmployeeDao);
        Dao<Task, List<Task>, Integer> taskDao = new TaskDao((JdbcTaskDao) jdbcTaskDao);
        Dao<TimekeepingEvent, List<TimekeepingEvent>, Integer> timekeepingEventDao = new EventDao((JdbcEventDao) jdbcEventDao);

        employeeManager = new EmployeeManager(employeeDao);
        taskManager = new TaskManager(taskDao);
        dayManager = new DayManager(employeeDao, taskDao, timekeepingEventDao);

        timekeepingReport = new TimekeepingReport(dayManager);
        timekeepingLog = new TimekeepingLog(dayManager);
    }

    private Employee getEmployee() {
        return searcher.getEmployeeByEmployeeAuthorizationData(employeeAuthorizationData);
    }

    public Employee logIn() {
        return getEmployee();
    }

    public Supervisor getSupervisor() {
        return new Supervisor(getEmployee(), employeeManager, timekeepingReport);
    }

    public Timekeeper getTimekeeper() {
        return new Timekeeper(getEmployee(), dayManager, taskManager, timekeepingLog);
    }

    public void createEmployee(Employee newEmployee) {
        try {
            if (!newEmployee.getDepartment().equals(getSupervisor().getEmployee().getDepartment())) {
                newEmployee.setDepartment(getSupervisor().getEmployee().getDepartment());
            }
            getSupervisor().getEmployeeManager().createEmployee(newEmployee);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void createEvent(Employee employee, String type, String time, String date) {
        employee.getTimekeepingEventManager().createEvent(employee.getEmployeeID(), type, time, date);
    }

    private String getInformationAboutEmployee(Employee employee) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(String.valueOf(employee.getEmployeeID()));
        stringJoiner.add(employee.getName());
        stringJoiner.add(employee.getSurname());
        stringJoiner.add(employee.getTelephoneNumber());
        stringJoiner.add(employee.getEmployeeStatusEnum().toString());
        stringJoiner.add(employee.getDepartment().getDepartmentName());
        stringJoiner.add(employee.getDepartment().getBranchOffice().getBranchOfficeName());
        return stringJoiner.toString();
    }

    private Employee searchEmployee(int employeeID) {
        return searcher.getEmployeeByID(employeeID);
    }

    private static class DataSourceImpl implements DataSource {
        private final Driver DRIVER;

        private final String INIT_SQL = "CREATE SCHEMA IF NOT EXISTS app;\n" +
                "USE app;\n" +
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                "  employee_id      INT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "  name             VARCHAR(100)                   NOT NULL,\n" +
                "  telephone_number VARCHAR(100)                   NOT NULL,\n" +
                "  employee_status  VARCHAR(100)                   NOT NULL,\n" +
                "  login            VARCHAR(100)                   NOT NULL,\n" +
                "  password         VARCHAR(100)                   NOT NULL,\n" +
                "  department       VARCHAR(100)                   NOT NULL,\n" +
                "  branch_office    VARCHAR(100)                   NOT NULL\n" +
                ");";

        public DataSourceImpl() throws SQLException {
            DRIVER = new org.h2.Driver();
            Properties properties = new Properties();
            properties.setProperty("user", "user");
            properties.setProperty("password", "password");
            Connection connection = DRIVER.connect("jdbc:h2:~/employees", properties);
            Statement statement = connection.createStatement();
            statement.execute(INIT_SQL);
            statement.close();
            connection.close();
        }

        @Override
        public Connection getConnection() throws SQLException {
            Properties properties = new Properties();
            properties.setProperty("user", "user");
            properties.setProperty("password", "password");
            return DRIVER.connect("jdbc:h2:~/employees", properties);
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            Properties properties = new Properties();
            properties.setProperty(username, username);
            properties.setProperty(password, password);
            return DRIVER.connect("jdbc:h2:~/employees", properties);
        }

        @Override
        public <T> T unwrap(Class<T> aClass) {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> aClass) {
            return false;
        }

        @Override
        public PrintWriter getLogWriter() {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter printWriter) {

        }

        @Override
        public void setLoginTimeout(int seconds) {

        }

        @Override
        public int getLoginTimeout() {
            return 0;
        }

        @Override
        public Logger getParentLogger() {
            return null;
        }
    }
}
