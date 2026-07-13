package com.hamza.weather.mapper;

import com.hamza.weather.dto.openmeteo.OpenMeteoResponse;
import com.hamza.weather.dto.osm.OsmResponse;
import com.hamza.weather.dto.response.WeatherResponse;
import com.hamza.weather.enums.WeatherCondition;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherResponse toResponse(OpenMeteoResponse meteoResponse, OsmResponse osmResponse) {

        String countryName = "Unknown";
        String cityName = "Unknown";

        if (osmResponse != null && osmResponse.address() != null) {
            countryName = osmResponse.address().country();
            cityName = osmResponse.address().getResolvedCity();
        }

        return WeatherResponse.builder()
                .country(countryName)
                .city(cityName)
                .temperature(meteoResponse.getCurrentWeather().getTemperature())
                .windSpeed(meteoResponse.getCurrentWeather().getWindSpeed())
                .condition(WeatherCondition.fromCode(meteoResponse.getCurrentWeather().getWeatherCode()).getDescription())
                .build();
    }
}