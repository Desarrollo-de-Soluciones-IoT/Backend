package com.docseeker.backend.repository;

import com.docseeker.backend.model.Appointment;
import org.springframework.data.repository.ListCrudRepository;

public interface AppointmentRepository extends ListCrudRepository<Appointment, Integer> {
}
