package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.CommonDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

@Repository
public abstract class CommonDAOImpl<T> implements CommonDAO<T> {
    protected SessionFactory sessionFactory;

    protected Class<T> persistentClass;

    public CommonDAOImpl(Class<T> entityClass) {
        this.persistentClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    @Override
    public void addEntity(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateEntity(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteEntity(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
