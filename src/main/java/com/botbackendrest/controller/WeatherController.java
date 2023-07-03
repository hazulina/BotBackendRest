package com.botbackendrest.controller;

import com.botbackendrest.entity.WeatherDto;
import com.botbackendrest.service.weatherService.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecasts")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{cityName}/{lang}")
    public WeatherDto getCurrentWeather(@PathVariable String cityName, @PathVariable String lang){
            return weatherService.getCurrentWeatherByCityName(cityName, lang);
    }

}
