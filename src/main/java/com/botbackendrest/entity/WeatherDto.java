package com.botbackendrest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WeatherDto {
    private int cityId;
    private String cityName;
    private String weatherType;
    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLikeTemp;
    private BigDecimal windSpeed;


    public WeatherDto() {

    }
}
