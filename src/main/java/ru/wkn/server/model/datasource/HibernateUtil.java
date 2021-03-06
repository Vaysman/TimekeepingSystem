package ru.wkn.server.model.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.Ejb3Configuration;
import org.jetbrains.annotations.Contract;

public class HibernateUtil {

    private static final Log logger = (Log) LogFactory.getLog(HibernateUtil.class.getName());

    private static final SessionFactory sessionFactory;

    private static final Ejb3Configuration ejb3Configuration;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure("/META-INF/hibernate.cfg.xml")
                    .buildSessionFactory();
            ejb3Configuration = new Ejb3Configuration()
                    .configure("/META-INF/hibernate.cfg.xml");
        } catch (Throwable throwable) {
            logger.error("Initial SessionFactory creation failed." + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
    }

    @Contract(pure = true)
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Contract(pure = true)
    public static Ejb3Configuration getEjb3Configuration() {
        return ejb3Configuration;
    }
}
