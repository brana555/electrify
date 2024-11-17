package com.project.electrify.repository;

import com.project.electrify.model.RawOpenChargeMapApiResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawOpenChargeMapApiResponseRepository extends MongoRepository<RawOpenChargeMapApiResponse, String> {
}
