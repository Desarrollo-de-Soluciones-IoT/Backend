package com.docseeker.backend.repository;

import com.docseeker.backend.model.Patient;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends ListCrudRepository<Patient, Integer> {
}
