package com.botbackendrest.service.cityService;

import com.botbackendrest.dao.CityRepository;
import com.botbackendrest.entity.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImp implements CityService {
    private final CityRepository cityRepository;


    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCity(int id) {
        Optional<City> optional = cityRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void saveOrUpdateCity(City city) {
        cityRepository.save(city);

    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

}
