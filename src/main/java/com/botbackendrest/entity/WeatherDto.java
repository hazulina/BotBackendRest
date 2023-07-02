package com.botbackendrest.entity;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeatherDto {
    private int cityId;
    private String cityName;
    private String weatherType;
    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLikeTemp;
    private BigDecimal windSpeed;

    private byte[] pictureFromCloudStorage;


    public WeatherDto() {

    }

    public WeatherDto(int cityId, String cityName, String weatherType, String description,
                      BigDecimal temperature, BigDecimal feelsLikeTemp, BigDecimal windSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weatherType = weatherType;
        this.description = description;
        this.temperature = temperature;
        this.feelsLikeTemp = feelsLikeTemp;
        this.windSpeed = windSpeed;
    }
}
