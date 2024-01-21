package com.Plus24.GeoLocator.GeoLocator.Services;

import com.Plus24.GeoLocator.GeoLocator.Dtos.LocationDto;
import com.Plus24.GeoLocator.GeoLocator.Raws.Location;
import com.Plus24.GeoLocator.GeoLocator.Repositories.LocationRepositpory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LocationService {

    private final LocationRepositpory locationRepositpory;

    public LocationService(LocationRepositpory _locationRepositpory){
        this.locationRepositpory = _locationRepositpory;
    }

    public LocationDto getLocationByAddress(String address) {
        Location location = locationRepositpory.findByAddress(address);
        if(location == null)
            return null;
        else
            return new LocationDto(location);
    }

    public boolean save(LocationDto locationDto) {
        try {
            Location location = new Location(locationDto.getAddress(), locationDto.getLat(),locationDto.getLon());
            locationRepositpory.save(location);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
