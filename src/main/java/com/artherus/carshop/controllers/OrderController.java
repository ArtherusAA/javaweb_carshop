package com.artherus.carshop.controllers;

import com.artherus.carshop.DAO.CarDAO;
import com.artherus.carshop.DAO.ClientDAO;
import com.artherus.carshop.DAO.OrderDAO;
import com.artherus.carshop.DAO.impl.CarDAOImpl;
import com.artherus.carshop.DAO.impl.ClientDAOImpl;
import com.artherus.carshop.DAO.impl.OrderDAOImpl;
import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class OrderController {

    @Autowired
    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final CarDAO carDAO = new CarDAOImpl();

    @GetMapping("/order")
    public String orderPage(@RequestParam(name = "orderId") Integer orderId, Model model) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        if (order == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Заказ с номером %d не зарегистрирован.", orderId));
            return "errorPage";
        }
        model.addAttribute("order", order);
        model.addAttribute("orderDAO", orderDAO);
        return "order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(
            @RequestParam(name = "orderId") Integer orderId,
            @RequestParam(name = "clientId") Integer clientId,
            @RequestParam(name = "carId") Integer carId,
            @RequestParam(name = "datetime") String datetime,
            @ModelAttribute("isTestDriveNeccesary") String isTestDriveNeccesary,
            @RequestParam(name = "status") String status,
            Model model
    ) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        if (order == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Заказ с номером %d не зарегистрирован.", orderId));
            return "errorPage";
        }
        Client client = clientDAO.getClientById(clientId);
        if (client == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Клиента с номером %d нет в салоне", clientId));
            return "errorPage";
        }
        Car car = carDAO.getCarById(carId);
        if (car == null) {
            model.addAttribute("error_message", String.format("Машины с номером %d нет в салоне", carId));
            return "errorPage";
        }
        order.setClient(client);
        order.setDate_time(Timestamp.valueOf(datetime.replace('T', ' ')));
        order.setCar_id(car);
        order.setStatus(status);
        order.setIs_test_drive_neccesary(isTestDriveNeccesary != null && !isTestDriveNeccesary.isEmpty());
        orderDAO.updateEntity(order);
        return "redirect:/clients";
    }

    @GetMapping("/addOrder")
    public String addOrderPage(Model model) {
        return "addOrder";
    }

    @PostMapping("/createOrder")
    public String createOrder(
            @RequestParam(name = "clientId") Integer clientId,
            @RequestParam(name = "carId") Integer carId,
            @RequestParam(name = "datetime") String datetime,
            @ModelAttribute("isTestDriveNeccesary") String isTestDriveNeccesary,
            @RequestParam(name = "status") String status,
            Model model
    ) {
        datetime = datetime + ":00";
        Client client = clientDAO.getClientById(clientId);
        if (client == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Клиента с номером %d нет в салоне", clientId));
            return "errorPage";
        }
        Car car = carDAO.getCarById(carId);
        if (car == null) {
            model.addAttribute("error_message", String.format("Машины с номером %d нет в салоне", carId));
            return "errorPage";
        }
        Order order = new Order(null, Timestamp.valueOf(datetime.replace('T', ' ')), client,
                car, isTestDriveNeccesary != null && !isTestDriveNeccesary.isEmpty(), status);
        orderDAO.addEntity(order);
        return "redirect:/clients";
    }
}
