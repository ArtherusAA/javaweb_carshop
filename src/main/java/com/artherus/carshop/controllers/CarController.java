package com.artherus.carshop.controllers;

import com.artherus.carshop.DAO.CarDAO;
import com.artherus.carshop.DAO.CarModelDAO;
import com.artherus.carshop.DAO.impl.CarDAOImpl;
import com.artherus.carshop.DAO.impl.CarModelDAOImpl;
import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private final CarDAO carDAO = new CarDAOImpl();

    @Autowired
    private final CarModelDAO modelDAO = new CarModelDAOImpl();

    @GetMapping("/addCar")
    public String addCarPage(Model model) {
        model.addAttribute("modelDAO", modelDAO);
        return "addCar";
    }

    @GetMapping("/cars")
    public String carListPage(Model model) {
        List<Car> cars = (List<Car>)carDAO.getAll();
        model.addAttribute("cars", cars);
        model.addAttribute("carDAO", carDAO);
        return "cars";
    }

    @GetMapping("/car")
    public String carPage(@RequestParam(name = "carId") Integer carId, Model model) {
        Car car = carDAO.getCarById(carId);
        if (car == null) {
            model.addAttribute("error_message", String.format("Машины с номером %d нет в салоне", carId));
            return "errorPage";
        }
        model.addAttribute("car", car);
        model.addAttribute("carDAO", carDAO);
        return "car";
    }

    @PostMapping("/createCar")
    public String createCar(@RequestParam(name = "carModelStr") String carModelStr,
                            @RequestParam(name = "carColor") String color,
                            @RequestParam(name = "price") Integer price,
                            Model model) {
        List<CarModel> carModel = modelDAO.getCarModelsByName(carModelStr);
        if (carModel.size() == 0) {
            model.addAttribute("error_message", String.format("Модель %s отсутствует базе", carModelStr));
            return "errorPage";
        }
        carDAO.addEntity(new Car(null, carModel.get(0), "", "", color, "",
                        new Timestamp(0), 0, new BigDecimal(price)));
        return "redirect:/cars";
    }

    @PostMapping("/saveCar")
    public String saveCar(@RequestParam(name = "regId") Integer regId,
                          @RequestParam(name = "carModel") String carModelStr,
                          @RequestParam(name = "carColor") String color,
                          Model model) {
        Car car = carDAO.getCarById(regId);
        if (car == null) {
            model.addAttribute("error_message", String.format("Машины с номером %d нет в салоне", regId));
            return "errorPage";
        }
        car.setColor(color);
        List<CarModel> carModel = modelDAO.getCarModelsByName(carModelStr);
        if (carModel.size() == 0) {
            model.addAttribute("error_message", String.format("Нет модели %s", carModelStr));
            return "errorPage";
        }
        car.setModel_id(carModel.get(0));
        carDAO.updateEntity(car);
        return String.format("redirect:/car?carId=%d", regId);
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam(name = "regId") Integer regId,
                            Model model) {
        Car car = carDAO.getCarById(regId);
        if (car == null) {
            model.addAttribute("error_message", String.format("Машины с номером %d нет в салоне", regId));
            return "errorPage";
        }
        carDAO.deleteEntity(car);
        return "redirect:/cars";
    }
}
