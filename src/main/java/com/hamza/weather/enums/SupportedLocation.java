package com.hamza.weather.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SupportedLocation {

    AMMAN("Amman", 31.95, 35.91),
    DUBAI("Dubai", 25.20, 55.27),
    CAIRO("Cairo", 30.04, 31.23),
    RIYADH("Riyadh", 24.71, 46.67);

    private final String cityName;
    private final double latitude;
    private final double longitude;

    public static SupportedLocation fromName(String name) {
        return Arrays.stream(values())
                .filter(loc -> loc.getCityName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("City not supported: " + name));
    }
}