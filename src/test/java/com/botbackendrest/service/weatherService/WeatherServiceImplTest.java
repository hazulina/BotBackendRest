package com.botbackendrest.service.weatherService;

import com.botbackendrest.entity.WeatherDto;
import com.botbackendrest.service.pictureService.PictureServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class WeatherServiceImplTest {
    @SpyBean
    private WeatherServiceImpl weatherService;
    @SpyBean
    private PictureServiceImpl pictureService;

    WeatherDto generateTestDto() {
        WeatherDto testDto = new WeatherDto("cityNotFound");
        testDto.setPictureFromCloudStorage(pictureService.getPictureById(testDto.getWeatherType().toLowerCase(), "1").toByteArray());
        return testDto;
    }

    @Test
    void getCurrentWeatherByCityName() {
        Assertions.assertEquals(generateTestDto(), weatherService.getCurrentWeatherByCityName("fhsjh", "en"));
    }
}