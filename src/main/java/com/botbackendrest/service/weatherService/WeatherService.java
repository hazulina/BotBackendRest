package com.botbackendrest.service.weatherService;

import com.botbackendrest.entity.WeatherDto;
import org.springframework.http.ResponseEntity;

public interface WeatherService {
    String getCurrWeatherByCityName(String cityName, String lang);
    WeatherDto getCurrWeatherByCityId(int cityId);
}
