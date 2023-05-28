package com.botbackendrest.controller;

import com.botbackendrest.service.weatherService.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/forecasts/{cityName}/{lang}")
    public String getCurrWeather(@PathVariable String cityName,@PathVariable String lang){
        try{
            return weatherService.getCurrWeatherByCityName(cityName, lang);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
