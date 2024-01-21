package com.Plus24.GeoLocator.GeoLocator.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty(value="location")
    Location location;

    @JsonProperty(value = "location_type")
    String locationType;
}
