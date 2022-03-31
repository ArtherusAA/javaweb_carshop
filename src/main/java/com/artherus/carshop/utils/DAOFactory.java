package com.artherus.carshop.utils;

import com.artherus.carshop.DAO.CarDAO;
import com.artherus.carshop.DAO.CarModelDAO;
import com.artherus.carshop.DAO.ClientDAO;
import com.artherus.carshop.DAO.OrderDAO;
import com.artherus.carshop.DAO.impl.CarDAOImpl;
import com.artherus.carshop.DAO.impl.CarModelDAOImpl;
import com.artherus.carshop.DAO.impl.ClientDAOImpl;
import com.artherus.carshop.DAO.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory instance = null;
    private static CarDAO carDAO = null;
    private static CarModelDAO carModelDAO = null;
    private static ClientDAO clientDAO = null;
    private static OrderDAO orderDAO = null;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public static CarDAO getCarDAO() {
        if (carDAO == null) {
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }

    public static CarModelDAO getCarModelDAO() {
        if (carModelDAO == null) {
            carModelDAO = new CarModelDAOImpl();
        }
        return carModelDAO;
    }

    public static ClientDAO getClientDAO() {
        if (clientDAO == null) {
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }

    public static OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            orderDAO = new OrderDAOImpl();
        }
        return orderDAO;
    }
}
