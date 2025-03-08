package com.project.electrify.service;

import com.project.electrify.model.City;
import com.project.electrify.repository.ChargingStationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CityService {
    private final ChargingStationRepository chargingStationRepository;

    public City getByLatitudeLongitude(final double latitude, final double longitude) {
        return chargingStationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public City getByCity(final String city) {
        return chargingStationRepository.findByCity(city);
    }
}
