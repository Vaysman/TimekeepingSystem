package ru.wkn.server.model.datasource.dao;

import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.util.List;

public interface Dao<V, L, K> {

    V create(V newInstance) throws PersistentException;

    L read(K id) throws PersistentException;

    void update(V transientObject) throws PersistentException;

    void delete(V persistentObject) throws PersistentException;

    List<V> getAll() throws PersistentException;
}
