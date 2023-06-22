package com.botbackendrest.service.weatherService;

import com.botbackendrest.entity.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weatherApi.query.url}")
    private String weatherUrl;
    @Value("${weatherApi.key}")
    private String apiKey;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Override
    public String getCurrentWeatherByCityName(String cityName, String lang) {
        URI url = new UriTemplate(weatherUrl).expand(cityName, apiKey, lang);
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
            System.out.println(response.getBody());
            return convertDtoToJson(convertJsonToDto(response.getBody()));
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAsString();
        }
    }


    @Override
    public WeatherDto getCurrentWeatherByCityId(int cityId) {         //TODO
        return null;
    }

    private WeatherDto convertJsonToDto(String response) {
        try {
            JsonNode node = objectMapper.readTree(response);
            return new WeatherDto(
                    node.path("id").asInt(),
                    node.path("name").asText(),
                    node.path("weather").get(0).path("main").asText(),
                    node.path("weather").get(0).path("description").asText(),
                    BigDecimal.valueOf(node.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(node.path("main").path("feels_like").asDouble()),
                    BigDecimal.valueOf(node.path("wind").path("speed").asDouble()));

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    private String convertDtoToJson(WeatherDto weatherDto) {
        try {
            return objectMapper.writeValueAsString(weatherDto);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WeatherServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }
}
