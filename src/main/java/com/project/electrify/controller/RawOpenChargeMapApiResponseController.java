package com.project.electrify.controller;

import com.project.electrify.model.RawOpenChargeMapApiResponse;
import com.project.electrify.service.RawOpenChargeMapApiResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class RawOpenChargeMapApiResponseController {
    private final RawOpenChargeMapApiResponseService service;

    @GetMapping("/api/rawOpenChargeMapApiResponse/{id}")
    public Optional<RawOpenChargeMapApiResponse> getById(@PathVariable String id) {
        return service.findById(id);
    }
}
