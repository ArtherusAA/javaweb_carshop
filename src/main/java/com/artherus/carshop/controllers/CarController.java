package com.artherus.carshop.controllers;

import com.artherus.carshop.DAO.CarDAO;
import com.artherus.carshop.DAO.impl.CarDAOImpl;
import com.artherus.carshop.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private final CarDAO carDAO = new CarDAOImpl();

    @GetMapping("/cars")
    public String carListPage(Model model) {
        List<Car> cars = (List<Car>)carDAO.getAll();
        model.addAttribute("cars", cars);
        model.addAttribute("carDAO", carDAO);
        return "cars";
    }
}
