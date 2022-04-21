package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.CarDAO;
import com.artherus.carshop.DAO.CommonDAO;
import com.artherus.carshop.models.Car;
import com.artherus.carshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CarDAOImpl extends CommonDAOImpl<Car> implements CarDAO {

    public CarDAOImpl() {
        super(Car.class);
    }

    @Override
    public List<Car> getCarByModel(String model) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Car> query = session.createQuery("FROM Car WHERE model_id.model " +
                "LIKE :model", Car.class).setParameter("model", "%" + model + "%");
        return query.getResultList();
    }

    @Override
    public List<Car> getCarByManufacturer(String manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Car> query = session.createQuery("FROM Car WHERE model_id.manufacturer " +
                "LIKE :manufacturer", Car.class).
                setParameter("manufacturer", "%" + manufacturer + "%");
        return query.getResultList();
    }

    @Override
    public Car getCarById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Car> query = session.createQuery("FROM Car WHERE reg_id = :id", Car.class)
                .setParameter("id", id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Car> getCarByColor(String color) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Car> query = session.createQuery("FROM Car WHERE color = :color",
                Car.class).setParameter("color", color);
        return query.getResultList();
    }

    @Override
    public List<Car> getCarInPriceRange(BigDecimal start, BigDecimal finish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Car> query = session.createQuery("FROM Car WHERE :start <= price" +
                        " AND price <= :finish", Car.class)
                .setParameter("start", start).setParameter("finish", finish);
        return query.getResultList();
    }

}
