package com.botbackendrest.controller;

import com.botbackendrest.entity.WeatherDto;
import com.botbackendrest.service.weatherService.WeatherService;
import com.botbackendrest.util.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @InjectMocks
    private RandomUtils utilitiesTest;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void testGetCurrentWeather() throws Exception {
        String cityName = "City 1";
        String lang = "en";

        WeatherDto weatherDto = generateTestWeatherDto();

        Mockito.when(weatherService.getCurrentWeatherByCityName(cityName, lang)).thenReturn(weatherDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/forecasts/{cityName}/{lang}", cityName, lang))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityId").value(weatherDto.getCityId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").value(weatherDto.getCityName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weatherType").value(weatherDto.getWeatherType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(weatherDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(weatherDto.getTemperature().doubleValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.feelsLikeTemp").value(weatherDto.getFeelsLikeTemp().doubleValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(weatherDto.getWindSpeed().doubleValue()));

        Mockito.verify(weatherService, Mockito.times(1)).getCurrentWeatherByCityName(cityName, lang);
    }

    private WeatherDto generateTestWeatherDto() {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCityId(123);
        weatherDto.setCityName("City " + utilitiesTest.generateRandomString(7));
        weatherDto.setWeatherType("Sunny");
        weatherDto.setDescription("Clear sky");
        weatherDto.setTemperature(BigDecimal.valueOf(utilitiesTest.generateRandomDouble(1.0, 90.0)));
        weatherDto.setFeelsLikeTemp(BigDecimal.valueOf(utilitiesTest.generateRandomDouble(1.0, 80.0)));
        weatherDto.setWindSpeed(BigDecimal.valueOf(utilitiesTest.generateRandomDouble(1.0, 20.0)));
        return weatherDto;
    }

}

