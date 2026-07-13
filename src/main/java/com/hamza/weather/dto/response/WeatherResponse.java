package com.hamza.weather.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    private String country;
    private String city;
    private double temperature;
    private double windSpeed;
    private String condition;
}