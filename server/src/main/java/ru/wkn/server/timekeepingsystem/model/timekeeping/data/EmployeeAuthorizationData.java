package ru.wkn.server.timekeepingsystem.model.timekeeping.data;

import java.util.Objects;

public class EmployeeAuthorizationData {

    private String login;
    private String password;

    public EmployeeAuthorizationData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAuthorizationData that = (EmployeeAuthorizationData) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "EmployeeAuthorizationData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
