package com.botbackendrest.service.weatherService;

import com.botbackendrest.entity.WeatherDto;
import com.botbackendrest.service.pictureService.PictureService;
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
import java.util.Random;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weatherApi.query.url}")
    private String weatherUrl;
    @Value("${weatherApi.key}")
    private String apiKey;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final PictureService pictureService;

    @Override
    public WeatherDto getCurrentWeatherByCityName(String cityName, String lang) {
        URI url = new UriTemplate(weatherUrl).expand(cityName, apiKey, lang);
        ResponseEntity<String> response;
        WeatherDto weatherDto = new WeatherDto();
        try {
            response = restTemplate.getForEntity(url, String.class);
            log.info(response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                weatherDto = convertJsonToDto(response.getBody());
            }
        } catch (HttpClientErrorException exception) {
            weatherDto = getEmptyWeatherDto("null");
        }
        return weatherDto;
    }

    private WeatherDto getEmptyWeatherDto(String aNull) {
        WeatherDto weatherDto = new WeatherDto(aNull);
        weatherDto.setPictureFromCloudStorage(
                pictureService.getPictureById(
                                weatherDto.getWeatherType().toLowerCase(),
                                String.valueOf(getRandomPictureFromCloudStorage()))
                        .toByteArray());
        return weatherDto;
    }


    @Override
    public WeatherDto getCurrentWeatherByCityId(int cityId) {         //TODO
        return null;
    }

    private WeatherDto convertJsonToDto(String response) {
        try {
            JsonNode node = objectMapper.readTree(response);
            JsonNode weatherNode = node.path("weather").get(0);
            JsonNode mainNode = node.path("main");
            JsonNode windNode = node.path("wind");

            WeatherDto weatherDto = new WeatherDto(
                    node.path("id").asInt(),
                    node.path("name").asText(),
                    weatherNode.path("main").asText(),
                    weatherNode.path("description").asText(),
                    BigDecimal.valueOf(mainNode.path("temp").asDouble()),
                    BigDecimal.valueOf(mainNode.path("feels_like").asDouble()),
                    BigDecimal.valueOf(windNode.path("speed").asDouble())
            );

            weatherDto.setPictureFromCloudStorage(
                    pictureService.getPictureById(
                                    weatherDto.getWeatherType().toLowerCase(),
                                    String.valueOf(getRandomPictureFromCloudStorage()))
                            .toByteArray());

            return weatherDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    private int getRandomPictureFromCloudStorage() {
        Random random = new Random();
        return random.nextInt(16 - 1) + 1;
    }


    public WeatherServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate, PictureService pictureService) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        this.pictureService = pictureService;
    }
}
