package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.OrderDAO;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
import com.artherus.carshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public List<Order> getOrdersByClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> query = session.createQuery("FROM Order WHERE order_id = :id", Order.class).
                setParameter("id", client.getClient_id());
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersByDateRange(Timestamp start, Timestamp finish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> query = session.createQuery(
                "FROM Order WHERE :start <= date_time AND date_time <= :finish",
                Order.class).
                setParameter("start", start).setParameter("finish", finish);
        return query.getResultList();
    }

}
