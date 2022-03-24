package com.artherus.carshop.DAO;

import com.artherus.carshop.models.CarModel;

import java.util.List;

public interface CarModelDAO {

    void addCarModel(CarModel model);
    void updateCarModel(CarModel model);
    void deleteCarModel(CarModel model);

    List<CarModel> getCarModelsByName(String name);
    List<CarModel> getCarModelsByManufacturer(String manufacturer);

}
