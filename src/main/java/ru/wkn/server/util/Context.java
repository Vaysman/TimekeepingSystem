package ru.wkn.server.util;

public class Context {

    private SessionsManager sessionsManager;
    private boolean stopFlag;


    public Context(boolean stopFlag, SessionsManager sessionsManager) {
        this.stopFlag = stopFlag;
        this.sessionsManager = sessionsManager;
    }

    public SessionsManager getSessionsManger() {
        return sessionsManager;
    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
