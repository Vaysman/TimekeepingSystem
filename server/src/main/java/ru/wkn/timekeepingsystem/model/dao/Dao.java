package ru.wkn.timekeepingsystem.model.dao;

import ru.wkn.timekeepingsystem.model.dao.persistent.PersistentException;

import java.util.List;

public interface Dao<V> {

    V create(V newInstance) throws PersistentException;

    V read(int id) throws PersistentException;

    void update(V transientObject) throws PersistentException;

    void delete(V persistentObject) throws PersistentException;

    List<V> getAll() throws PersistentException;
}
