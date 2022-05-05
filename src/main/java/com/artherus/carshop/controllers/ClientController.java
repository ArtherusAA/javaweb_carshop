package com.artherus.carshop.controllers;

import com.artherus.carshop.DAO.ClientDAO;
import com.artherus.carshop.DAO.OrderDAO;
import com.artherus.carshop.DAO.impl.ClientDAOImpl;
import com.artherus.carshop.DAO.impl.OrderDAOImpl;
import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final OrderDAO orderDAO = new OrderDAOImpl();

    @GetMapping("/clients")
    public String clientsListPage(Model model) {
        List<Client> clients = (List<Client>)clientDAO.getAll();
        model.addAttribute("clients", clients);
        model.addAttribute("clientDAO", clientDAO);
        model.addAttribute("orderDAO", orderDAO);
        return "clients";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "clientId") Integer clientId, Model model) {
        Client client = clientDAO.getClientById(clientId);
        if (client == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Клиента с номером %d нет в салоне", clientId));
            return "errorPage";
        }
        model.addAttribute("client", client);
        model.addAttribute("clientDAO", clientDAO);
        return "client";
    }

    @PostMapping("/saveClient")
    public String saveClient(
            @RequestParam(name = "clientId") Integer clientId,
            @RequestParam(name = "clientName") String clientName,
            @RequestParam(name = "clientAddress") String clientAddress,
            @RequestParam(name = "clientPhone") String clientPhone,
            @RequestParam(name = "clientEmail") String clientEmail,
            Model model
    ) {
        Client client = clientDAO.getClientById(clientId);
        if (client == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Клиента с номером %d нет в салоне", clientId));
            return "errorPage";
        }
        client.setName(clientName);
        client.setAddress(clientAddress);
        client.setPhone(clientPhone);
        client.setEmail(clientEmail);
        clientDAO.updateEntity(client);
        return String.format("redirect:/client?clientId=%d", clientId);
    }

    @PostMapping("/deleteClient")
    public String deleteClient(@RequestParam(name = "clientId") Integer clientId, Model model) {
        Client client = clientDAO.getClientById(clientId);
        if (client == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Клиента с номером %d нет в салоне", clientId));
            return "errorPage";
        }
        clientDAO.deleteEntity(client);
        return "redirect:/clients";
    }

    @GetMapping("/addClient")
    public String addClientPage(Model model) {
        return "addClient";
    }

    @PostMapping("/createClient")
    public String createClient(
            @RequestParam(name = "clientName") String clientName,
            @RequestParam(name = "clientAddress") String clientAddress,
            @RequestParam(name = "clientPhone") String clientPhone,
            @RequestParam(name = "clientEmail") String clientEmail,
            Model model
    ) {
        clientDAO.addEntity(new Client(null, clientName, clientAddress, clientPhone, clientEmail));
        return "redirect:/clients";
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(@RequestParam(name = "orderId") Integer orderId, Model model) {
        Order order = orderDAO.getOrderByOrderId(orderId);
        if (order == null) {
            model.addAttribute(
                    "error_message",
                    String.format("Заказ с номером %d не зарегистрирован.", orderId));
            return "errorPage";
        }
        orderDAO.deleteEntity(order);
        return "redirect:/clients";
    }

}
