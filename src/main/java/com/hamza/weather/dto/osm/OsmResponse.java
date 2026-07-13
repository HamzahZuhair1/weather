package com.hamza.weather.dto.osm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OsmResponse(Address address) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Address(String country, String city, String town, String village) {
        public String getResolvedCity() {
            if (city != null) return city;
            if (town != null) return town;
            return village != null ? village : "Unknown";
        }
    }
}