package ru.wkn.server;

import ru.wkn.server.util.Context;
import ru.wkn.server.util.Server;
import ru.wkn.server.util.SessionsManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerLauncher {

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/parameters/db-params.properties"));
            Server server = new Server(Integer.parseInt(properties.getProperty("port")), new Context(false, new SessionsManager()));
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
