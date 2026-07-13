package com.hamza.weather.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    private double temperature;
    private double windSpeed;
    private String condition;
}