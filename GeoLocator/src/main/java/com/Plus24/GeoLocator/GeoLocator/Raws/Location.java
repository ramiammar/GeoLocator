package com.Plus24.GeoLocator.GeoLocator.Raws;

import jakarta.persistence.*;

@Table(name = "location")
@Entity
public class Location {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "address",length = 500, nullable = false)
    private String address;

    @Column(name = "latitude",length = 500, nullable = false)
    private double lat;

    @Column(name = "longitude",length = 500, nullable = false)
    private double lon;

    public Location(Long id, String address, double lat, double lon) {
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }
    public Location(String address, double lat, double lon) {
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
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
