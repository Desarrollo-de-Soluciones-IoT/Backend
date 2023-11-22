package com.docseeker.backend.repository;

import com.docseeker.backend.model.Temperature;
import org.springframework.data.repository.ListCrudRepository;

public interface TemperatureRepository extends ListCrudRepository<Temperature, Integer> {
}
