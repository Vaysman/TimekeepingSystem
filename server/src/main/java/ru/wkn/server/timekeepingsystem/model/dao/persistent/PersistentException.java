package ru.wkn.server.timekeepingsystem.model.dao.persistent;

public class PersistentException extends Exception {

    public PersistentException(Throwable cause) {
        super(cause);
    }

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    protected PersistentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
