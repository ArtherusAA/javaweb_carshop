package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.OrderDAO;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
import com.artherus.carshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDAOImpl extends CommonDAOImpl<Order> implements OrderDAO {

    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public Order getOrderByOrderId(Integer order_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> orders = session.createQuery("FROM Order WHERE order_id = :order_id",
                Order.class).setParameter("order_id", order_id);
        if (orders.getResultList().size() == 0) {
            return null;
        }
        return orders.getResultList().get(0);
    }

    @Override
    public List<Order> getOrdersByClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> query = session.createQuery("FROM Order WHERE client = :client", Order.class).
                setParameter("client", client);
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
