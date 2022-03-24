package com.artherus.carshop.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory factory;
    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch(Throwable e) {
            System.err.println(String.format("Session factory creation failed: {}", e.getMessage()));
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
