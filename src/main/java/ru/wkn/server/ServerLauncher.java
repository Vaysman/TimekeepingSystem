package ru.wkn.server;

import ru.wkn.server.util.Context;
import ru.wkn.server.util.Server;
import ru.wkn.server.util.SessionsManager;

public class ServerLauncher {

    public static void main(String[] args) {
        Server server =  new Server(5000, new Context(false, new SessionsManager()));
        server.run();
    }
}
