package com.botbackendrest.controller;

import com.botbackendrest.entity.City;
import com.botbackendrest.service.cityService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping()
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable int id) {
        return cityService.getCity(id);
    }

    @PostMapping()
    public City addNewCity(@RequestBody City city) {
        cityService.saveOrUpdateCity(city);
        return city;
    }

    @PutMapping()
    public City updateCity(@RequestBody City city) {
        cityService.saveOrUpdateCity(city);
        return city;
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
        return "City with id: " + id + " was deleted";
    }

}
