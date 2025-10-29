package com.waduclay.learning.stub;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class WeatherService {
    private final IWeatherApi weatherApi;

    public WeatherService(IWeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public String getWeatherReport(String city) {
        if (!weatherApi.isServiceAvailable()){
            return "Weather service is currently unavailable";
        }
        double temperature = weatherApi.getTemperature(city);

        if (temperature < 0){
            return String.format("Freezing cold in %s: %s°C", city, temperature);
        } else if (temperature < 15) {
            return String.format("Cool in %s: %s°C", city, temperature);
        } else {
            return String.format("Warm in %s: %s°C", city, temperature);
        }
    }
}
