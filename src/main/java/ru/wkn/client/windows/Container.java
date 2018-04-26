package ru.wkn.client.windows;

public class Container {

    private static String login;
    private static String password;

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Container.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Container.password = password;
    }
}
