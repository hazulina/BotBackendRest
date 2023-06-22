package com.botbackendrest.controller;

import com.botbackendrest.service.weatherService.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecasts")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{cityName}/{lang}")
    public String getCurrentWeather(@PathVariable String cityName, @PathVariable String lang){
        try{
            return weatherService.getCurrentWeatherByCityName(cityName, lang);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
