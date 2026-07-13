package com.hamza.weather.dto.openmeteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenMeteoResponse {

    @JsonProperty("current_weather")
    private CurrentWeatherDto currentWeather;
}