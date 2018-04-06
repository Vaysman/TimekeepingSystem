package ru.wkn.timekeepingsystem.model.timekeeping.log.persistent;

import java.util.List;

public interface Dao<K, V> {

    V create(V newInstance) throws PersistentException;

    V read(K id) throws PersistentException;

    void update(V transientObject) throws PersistentException;

    void delete(V persistentObject) throws PersistentException;

    List<V> getAll() throws PersistentException;
}
