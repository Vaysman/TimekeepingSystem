package ru.wkn.server.model.datasource.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.wkn.server.model.datasource.HibernateUtil;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.util.List;

public class DaoTool<T> {

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Object createObject(Object newInstance) throws PersistentException {
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

    public T read(Integer id) throws PersistentException {
        Session session = null;
        T object = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            object = (T) session.load(object.getClass(), id);
        } catch (Exception e) {
            throw new PersistentException("Ошибка при чтении: ", e);
        } finally {
            closeSession(session);
        }
        return object;
    }

    public List<T> read(String s1, String s2, Integer id) throws PersistentException {
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

    public void update(Object transientObject) throws PersistentException {
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

    public void delete(Object persistentObject) throws PersistentException {
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

    public List<T> getAll() throws PersistentException {
        Session session = null;
        List<T> ts = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            ts = session.createCriteria(ts.getClass()).list();
        } catch (Exception e) {
            throw new PersistentException("Ошибка при чтении: ", e);
        } finally {
            closeSession(session);
        }
        return ts;
    }
}
