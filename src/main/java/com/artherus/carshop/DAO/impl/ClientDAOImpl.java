package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.ClientDAO;
import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
import com.artherus.carshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ClientDAOImpl extends CommonDAOImpl<Client> implements ClientDAO {

    @Override
    public Client getClientByOrderId(Integer order_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> orders = session.createQuery("FROM Order WHERE order_id = :order_id",
                        Order.class).setParameter("order_id", order_id);
        if (orders.getResultList().size() == 0) {
            return null;
        }
        return orders.getResultList().get(0).getClient();
    }

    @Override
    public List<Client> getClientsByOrderDateRange(Timestamp start, Timestamp finish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> order_query = session.createQuery(
                        "FROM Order WHERE :start <= date_time AND date_time <= :finish",
                        Order.class).
                setParameter("start", start).setParameter("finish", finish);
        List<Order> orders = order_query.getResultList();
        Set<Integer> client_ids = new HashSet<>();
        orders.forEach(order -> client_ids.add(order.getClient().getClient_id()));
        StringBuilder id_string = new StringBuilder("(");
        client_ids.forEach(id -> id_string.append(id.toString() + ","));
        if (id_string.charAt(id_string.length() - 1) == ',') {
            id_string.setCharAt(id_string.length() - 1, ')');
        } else {
            return null;
        }
        Query<Client> query = session.createQuery("FROM Client WHERE client_id IN " + id_string,
                Client.class);
        return query.getResultList();
    }

    @Override
    public List<Client> getClientsByOrderCar(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Order> order_query = session.createQuery(
                        "FROM Order WHERE car_id = :car",
                        Order.class).
                setParameter("car", car);
        List<Order> orders = order_query.getResultList();
        Set<Integer> client_ids = new HashSet<>();
        orders.forEach(order -> client_ids.add(order.getClient().getClient_id()));
        StringBuilder id_string = new StringBuilder("(");
        client_ids.forEach(id -> id_string.append(id.toString() + ","));
        if (id_string.charAt(id_string.length() - 1) == ',') {
            id_string.setCharAt(id_string.length() - 1, ')');
        } else {
            return null;
        }
        Query<Client> query = session.createQuery("FROM Client WHERE client_id IN " + id_string,
                Client.class);
        return query.getResultList();
    }

    @Override
    public Client getClientById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE client_id = :id",
                Client.class).setParameter("id", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Client> getClientsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE name LIKE :name", Client.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Client> getClientsByPhone(String phone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE phone LIKE :phone", Client.class)
                .setParameter("phone", "%" + phone + "%");
        return query.getResultList();
    }

    @Override
    public List<Client> getClientsByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE email LIKE :email", Client.class)
                .setParameter("email", "%" + email + "%");
        return query.getResultList();
    }
}
