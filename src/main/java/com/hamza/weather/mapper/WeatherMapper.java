package com.hamza.weather.mapper;

import com.hamza.weather.dto.openmeteo.OpenMeteoResponse;
import com.hamza.weather.dto.response.WeatherResponse;
import com.hamza.weather.enums.WeatherCondition;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherResponse toResponse(OpenMeteoResponse openMeteoResponse) {
        if (openMeteoResponse == null || openMeteoResponse.getCurrentWeather() == null) {
            throw new RuntimeException("Weather data is missing");
        }

        var current = openMeteoResponse.getCurrentWeather();

        return WeatherResponse.builder()
                .temperature(current.getTemperature())
                .windSpeed(current.getWindSpeed())
                .condition(WeatherCondition.fromCode(current.getWeatherCode()))
                .build();
    }
}