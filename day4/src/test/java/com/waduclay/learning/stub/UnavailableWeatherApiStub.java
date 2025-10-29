package com.waduclay.learning.stub;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
// A stub that simulates an unavailable service
public class UnavailableWeatherApiStub implements IWeatherApi {
    @Override
    public double getTemperature(String city) {
        throw new RuntimeException("Service unavailable");
    }

    @Override
    public boolean isServiceAvailable() {
        return false;
    }
}
