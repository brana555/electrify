package com.project.electrify.service;

import com.project.electrify.model.RawOpenChargeMapApiResponse;
import com.project.electrify.repository.RawOpenChargeMapApiResponseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RawOpenChargeMapApiResponseService {
    private final RawOpenChargeMapApiResponseRepository rawOpenChargeMapApiResponseRepository;

    public Optional<RawOpenChargeMapApiResponse> findById(final String id) {
        log.info("Finding by id={}", id);
        return rawOpenChargeMapApiResponseRepository.findById(id);
    }
}
