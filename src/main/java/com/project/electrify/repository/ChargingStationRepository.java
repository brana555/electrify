package com.project.electrify.repository;

import com.project.electrify.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChargingStationRepository extends MongoRepository<City, String> {
    City findByLatitudeAndLongitude(double latitude, double longitude);

    City findByCity(String city);
}
