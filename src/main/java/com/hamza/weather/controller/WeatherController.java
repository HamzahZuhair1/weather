package com.hamza.weather.controller;

import com.hamza.weather.dto.openmeteo.OpenMeteoResponse;
import com.hamza.weather.dto.osm.OsmResponse;
import com.hamza.weather.dto.response.WeatherResponse;
import com.hamza.weather.enums.SupportedLocation;
import com.hamza.weather.mapper.WeatherMapper;
import com.hamza.weather.service.WeatherIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherIntegrationService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping("/coordinates")
    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        Object[] rawData = weatherService.fetchWeatherAndLocation(latitude, longitude);
        var meteoData = (OpenMeteoResponse) rawData[0];
        var locationData = (OsmResponse) rawData[1];

        return ResponseEntity.ok(weatherMapper.toResponse(meteoData, locationData));
    }

    @GetMapping("/locations/{city}")
    public ResponseEntity<WeatherResponse> getWeatherByCity(
            @PathVariable String city) {

        var location = SupportedLocation.fromName(city);

        Object[] rawData = weatherService.fetchWeatherAndLocation(location.getLatitude(), location.getLongitude());
        var meteoData = (OpenMeteoResponse) rawData[0];
        var locationData = (OsmResponse) rawData[1];

        return ResponseEntity.ok(weatherMapper.toResponse(meteoData, locationData));
    }
}