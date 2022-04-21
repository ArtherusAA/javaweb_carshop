package com.artherus.carshop.DAO.impl;

import com.artherus.carshop.DAO.CarModelDAO;
import com.artherus.carshop.models.CarModel;
import com.artherus.carshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarModelDAOImpl extends CommonDAOImpl<CarModel> implements CarModelDAO {

    public CarModelDAOImpl() {
        super(CarModel.class);
    }

    @Override
    public List<CarModel> getCarModelsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<CarModel> query = session.createQuery("FROM CarModel WHERE model LIKE :name",
                        CarModel.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<CarModel> getCarModelsByManufacturer(String manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<CarModel> query = session.createQuery("FROM CarModel WHERE " +
                        "manufacturer LIKE :name", CarModel.class)
                .setParameter("name", "%" + manufacturer + "%");
        return query.getResultList();
    }
}
