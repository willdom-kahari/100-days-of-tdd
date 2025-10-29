package com.waduclay.learning.stub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Test
    @DisplayName("test warm weather")
    void testGetWeatherReportWarmWeather() {
        // Arrange
        IWeatherApi apiStub = new SuccessfulWeatherApiStub();
        WeatherService weatherService = new WeatherService(apiStub);

        // Act
        String report = weatherService.getWeatherReport("Harare");

        // Assert
        assertEquals("Warm in Harare: 22.5°C", report);
    }

    @Test
    @DisplayName("test weather service unavailable")
    void testGetWeatherReportServiceUnavailable() {
        // Arrange
        IWeatherApi apiStub = new UnavailableWeatherApiStub();
        WeatherService weatherService = new WeatherService(apiStub);

        // Act
        String report = weatherService.getWeatherReport("Harare");

        // Assert
        assertEquals("Weather service is currently unavailable", report);
    }

    @Mock
    private IWeatherApi weatherApi;
    @InjectMocks
    private WeatherService weatherService;

    // Using Mockito
    @Test
    @DisplayName("test freezing weather")
    void testGetWeatherReportFreezingWeather() {
        // Arrange
//        IWeatherApi weatherApi = mock(IWeatherApi.class);

        // Programme the stub's responses
        when(weatherApi.isServiceAvailable()).thenReturn(true);
        when(weatherApi.getTemperature("Harare")).thenReturn(-10.0);

//        WeatherService weatherService = new WeatherService(weatherApi);

        // Act
        String report = weatherService.getWeatherReport("Harare");

        assertEquals("Freezing cold in Harare: -10.0°C", report);
    }

}
