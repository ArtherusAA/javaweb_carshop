package com.artherus.carshop.DAO;

import com.artherus.carshop.models.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarDAO extends CommonDAO<Car> {

    List<Car> getCarByModel(String model);
    List<Car> getCarByManufacturer(String manufacturer);

    Car getCarById(Integer id);
    List<Car> getCarByColor(String color);
    List<Car> getCarInPriceRange(BigDecimal start, BigDecimal finish);

}
