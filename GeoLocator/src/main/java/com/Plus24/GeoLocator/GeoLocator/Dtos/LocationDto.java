package com.Plus24.GeoLocator.GeoLocator.Dtos;

import com.Plus24.GeoLocator.GeoLocator.Raws.Location;

public class LocationDto {

    private Long id;

    private String address;

    private double lat;

    private double lon;

    public LocationDto(Long id, String address, double lat, double lon) {
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }
    public LocationDto(String address, double lat, double lon) {
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public LocationDto() {
    }

    public LocationDto(Location location) {
        this.address = location.getAddress();
        this.lat = location.getLat();
        this.lon = location.getLon();
        this.id = this.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
