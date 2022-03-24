package com.artherus.carshop.DAO;

import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.Client;

import java.sql.Timestamp;
import java.util.List;

public interface ClientDAO {

    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);

    Client getClientByOrderId(Integer order_id);
    List<Client> getClientsByOrderDateRange(Timestamp start, Timestamp finish);
    List<Client> getClientsByOrderCar(Car car);

    Client getClientById(Integer id);
    List<Client> getClientsByName(String name);
    List<Client> getClientsByPhone(String phone);
    List<Client> getClientsByEmail(String email);

}
