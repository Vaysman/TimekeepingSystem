package ru.wkn.server.util;

import java.util.HashSet;
import java.util.Set;

public class SessionsManager {

    private final Set<ClientSession> clientSessions = new HashSet<>();

    public synchronized void addSession(ClientSession session) {
        clientSessions.add(session);
    }

    public synchronized void removeSession(ClientSession session) {
        clientSessions.remove(session);
    }
}