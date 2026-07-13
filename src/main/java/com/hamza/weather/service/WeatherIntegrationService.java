package com.hamza.weather.service;

import com.hamza.weather.dto.openmeteo.OpenMeteoResponse;
import com.hamza.weather.dto.osm.OsmResponse;
import com.hamza.weather.exception.ExternalApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherIntegrationService {

    private final RestClient weatherRestClient;
    private final RestClient osmRestClient = RestClient.create();

    @Cacheable(value = "weatherCache", key = "#latitude + '-' + #longitude")
    public Object[] fetchWeatherAndLocation(double latitude, double longitude) {

        OpenMeteoResponse weatherData = weatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current_weather", true)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new ExternalApiException("Failed to fetch weather data");
                })
                .body(OpenMeteoResponse.class);

        OsmResponse locationData = osmRestClient.get()
                .uri("https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude)
                .header("User-Agent", "WeatherApp/1.0")
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new ExternalApiException("Failed to fetch location data");
                })
                .body(OsmResponse.class);

        return new Object[]{weatherData, locationData};
    }
}