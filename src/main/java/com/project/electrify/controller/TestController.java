package com.project.electrify.controller;

import com.project.electrify.service.OpenChargeMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class TestController {

    @Value("${chargeMapKey}")
    private String chargeMapKey;

    private final OpenChargeMapService openChargeMapService;

    @Autowired
    public TestController(OpenChargeMapService openChargeMapService) {
        this.openChargeMapService = openChargeMapService;
    }

    @PostMapping("/get-charging-points-latitude-longitude")
    public String getChargingPointsByLatitudeLongitude(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "10") int distance) {
        return openChargeMapService.fetchChargingPointsByLatitudeLongitude(latitude, longitude, distance, chargeMapKey);
    }

    @GetMapping("/get-charging-points-city")
    public String getChargingPointsByCity(@RequestParam String city) {
        return openChargeMapService.fetchChargingPointsByCity(city);
    }
}
