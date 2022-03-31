package com.artherus.carshop.DAO;

import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO extends CommonDAO<Order> {

    List<Order> getOrdersByClient(Client client);
    List<Order> getOrdersByDateRange(Timestamp start, Timestamp finish);

}
