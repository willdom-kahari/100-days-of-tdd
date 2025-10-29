package com.waduclay.learning.stub;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
// A stub that simulates successful API responses
public class SuccessfulWeatherApiStub implements IWeatherApi {
    @Override
    public double getTemperature(String city) {
        return 22.5;
    }

    @Override
    public boolean isServiceAvailable() {
        return true;
    }
}
