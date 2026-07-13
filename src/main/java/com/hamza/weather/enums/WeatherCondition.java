package com.hamza.weather.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeatherCondition {

    CLEAR_SKY("Clear sky"),
    PARTLY_CLOUDY("Partly cloudy"),
    FOG("Fog"),
    RAIN("Rain"),
    SNOW("Snow"),
    UNKNOWN("Unknown");

    private final String description;

    public static WeatherCondition fromCode(int code) {
        return switch (code) {
            case 0 -> CLEAR_SKY;
            case 1, 2, 3 -> PARTLY_CLOUDY;
            case 45, 48 -> FOG;
            case 51, 53, 55, 61, 63, 65 -> RAIN;
            case 71, 73, 75 -> SNOW;
            default -> UNKNOWN;
        };
    }
}