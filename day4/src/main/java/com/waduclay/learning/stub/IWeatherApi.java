package com.waduclay.learning.stub;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface IWeatherApi {
    double getTemperature(String city);
    boolean isServiceAvailable();
}
