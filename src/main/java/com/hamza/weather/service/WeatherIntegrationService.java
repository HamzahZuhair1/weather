package com.hamza.weather.service;

import com.hamza.weather.dto.openmeteo.OpenMeteoResponse;
import com.hamza.weather.exception.ExternalApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherIntegrationService {

    private final RestClient weatherRestClient;

    public OpenMeteoResponse fetchWeather(double latitude, double longitude) {
        return weatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current_weather", true)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new ExternalApiException("Failed to fetch weather data from external provider");
                })
                .body(OpenMeteoResponse.class);
    }
}