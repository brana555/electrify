package com.project.electrify.repository;

import com.project.electrify.model.EVCharger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EVChargerRepository extends MongoRepository<EVCharger, String> {
}
