package ru.wkn.server.model.datasource.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.wkn.server.model.datasource.HibernateUtil;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.util.ArrayList;
import java.util.List;

class DaoTool<T> {

    private Class<T> typeClass;

    public DaoTool(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    Object createObject(Object newInstance) throws PersistentException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newInstance);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistentException("Ошибка при создании: ", e);
        } finally {
            closeSession(session);
        }
        return newInstance;
    }

    T read(Integer id) throws PersistentException {
        Session session = null;
        T object;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            object = (T) session.load(typeClass, id);
        } catch (Exception e) {
            throw new PersistentException("Ошибка при чтении: ", e);
        } finally {
            closeSession(session);
        }
        return object;
    }

    List<T> read(String s1, String s2, Integer id) throws PersistentException {
        Session session = null;
        List<T> ts;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(s1).setInteger(s2, id);
            ts = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistentException("Ошибка при чтении: ", e);
        } finally {
            closeSession(session);
        }
        return ts;
    }

    void update(Object transientObject) throws PersistentException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(transientObject);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistentException("Ошибка при обновлении: ", e);
        } finally {
            closeSession(session);
        }
    }

    void delete(Object persistentObject) throws PersistentException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(persistentObject);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistentException("Ошибка при удалении: ", e);
        } finally {
            closeSession(session);
        }
    }

    List<T> getAll() throws PersistentException {
        Session session = null;
        List<T> ts;
        try {
            if (HibernateUtil.getSessionFactory() != null) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if (session != null) {
                ts = session.createCriteria(typeClass).list();
                return ts;
            }
        } catch (Exception e) {
            throw new PersistentException("Ошибка при чтении: ", e);
        } finally {
            closeSession(session);
        }
        return new ArrayList<>();
    }
}
