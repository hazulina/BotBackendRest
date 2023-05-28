package com.botbackendrest.service.cityService;

import com.botbackendrest.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCity(int id);

    void saveOrUpdateCity(City city);

    void deleteCity(int id);
}
