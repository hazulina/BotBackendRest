package com.botbackendrest.controller;

import com.botbackendrest.entity.City;
import com.botbackendrest.service.cityService.CityService;
import com.botbackendrest.util.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @InjectMocks
    private RandomUtils utilitiesTest;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    private City generateTestCity() {
        City city = new City();
        city.setCityName(utilitiesTest.generateRandomString(10));
        city.setLatitude(utilitiesTest.generateRandomDouble(1.0, 90.0));
        city.setLongitude(utilitiesTest.generateRandomDouble(-180.0, 180.0));
        city.setZipcode(utilitiesTest.generateRandomInt(10000, 99999));
        return city;
    }

    @Test
    public void testGetAllCities() throws Exception {
        City city1 = generateTestCity();
        City city2 = generateTestCity();
        Mockito.when(cityService.getAllCities()).thenReturn(Arrays.asList(city1, city2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cityName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].longitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].zipcode").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cityName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].latitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].longitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].zipcode").isNumber());
    }

    @Test
    public void testGetCityById() throws Exception {
        int cityId = 1;
        City city = generateTestCity();
        Mockito.when(cityService.getCity(cityId)).thenReturn(city);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipcode").isNumber());
    }

    @Test
    public void testAddNewCity() throws Exception {
        City city = generateTestCity();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cityName\":\"" + city.getCityName() + "\",\"latitude\":" + city.getLatitude() + ",\"longitude\":" + city.getLongitude() + ",\"zipcode\":" + city.getZipcode() + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityId").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipcode").isNumber());

        Mockito.verify(cityService, times(1)).saveOrUpdateCity(city);
    }

    @Test
    public void testUpdateCity() throws Exception {
        City city = generateTestCity();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cityName\":\"" + city.getCityName() + "\",\"latitude\":" + city.getLatitude() + ",\"longitude\":" + city.getLongitude() + ",\"zipcode\":" + city.getZipcode() + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityId").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipcode").isNumber());

        Mockito.verify(cityService, times(1)).saveOrUpdateCity(city);
    }

    @Test
    public void testDeleteCity() throws Exception {
        int cityId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cities/{id}", cityId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("City with id: " + cityId + " was deleted"));

        Mockito.verify(cityService, times(1)).deleteCity(cityId);
    }
}
