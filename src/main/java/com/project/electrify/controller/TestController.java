package com.project.electrify.controller;

import com.project.electrify.service.OpenChargeMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/charging-points")
    public String getChargingPoints(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "10") int distance) {
        return openChargeMapService.fetchChargingPoints(latitude, longitude, distance, chargeMapKey);
    }
}
