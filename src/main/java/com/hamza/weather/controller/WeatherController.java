package com.hamza.weather.controller;

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

    // Endpoint 1: البحث عن طريق الإحداثيات مباشرة
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        var openMeteoResponse = weatherService.fetchWeather(latitude, longitude);
        return ResponseEntity.ok(weatherMapper.toResponse(openMeteoResponse));
    }

    // Endpoint 2: البحث عن طريق اسم المدينة
    @GetMapping("/locations/{city}")
    public ResponseEntity<WeatherResponse> getWeatherByCity(
            @PathVariable String city) {

        // تحويل النص إلى Enum لسحب الإحداثيات
        var location = SupportedLocation.fromName(city);

        // استخدام الإحداثيات لجلب الطقس
        var openMeteoResponse = weatherService.fetchWeather(location.getLatitude(), location.getLongitude());
        return ResponseEntity.ok(weatherMapper.toResponse(openMeteoResponse));
    }
}