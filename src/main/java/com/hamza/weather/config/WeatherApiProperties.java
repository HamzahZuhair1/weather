package com.hamza.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather.api.open-meteo")
public class WeatherApiProperties {

    private String baseUrl;
    private int connectTimeout;
    private int readTimeout;
}