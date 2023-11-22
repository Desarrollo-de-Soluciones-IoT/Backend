package com.docseeker.backend.repository;

import com.docseeker.backend.model.Pulse;
import org.springframework.data.repository.ListCrudRepository;

public interface PulseRepository extends ListCrudRepository<Pulse, Integer> {
}
