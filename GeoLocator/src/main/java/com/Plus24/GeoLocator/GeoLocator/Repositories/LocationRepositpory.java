package com.Plus24.GeoLocator.GeoLocator.Repositories;

import com.Plus24.GeoLocator.GeoLocator.Raws.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositpory  extends JpaRepository<Location,Long> {

    Location findByAddress(String address);
}
