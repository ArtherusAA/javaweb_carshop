package com.artherus.carshop.DAO;

import com.artherus.carshop.models.CarModel;

import java.util.List;

public interface CarModelDAO extends CommonDAO<CarModel> {

    List<CarModel> getCarModelsByName(String name);
    List<CarModel> getCarModelsByManufacturer(String manufacturer);

}
