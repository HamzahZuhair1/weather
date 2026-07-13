package com.hamza.weather.controller;

import com.hamza.weather.dto.response.WeatherResponse;
import com.hamza.weather.mapper.WeatherMapper;
import com.hamza.weather.service.WeatherIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherIntegrationService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        var openMeteoResponse = weatherService.fetchWeather(latitude, longitude);
        var weatherResponse = weatherMapper.toResponse(openMeteoResponse);

        return ResponseEntity.ok(weatherResponse);
    }
}