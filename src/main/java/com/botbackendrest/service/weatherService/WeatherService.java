package com.botbackendrest.service.weatherService;

import com.botbackendrest.entity.WeatherDto;

public interface WeatherService {
    WeatherDto getCurrentWeatherByCityName(String cityName, String lang);
    WeatherDto getCurrentWeatherByCityId(int cityId);
}
