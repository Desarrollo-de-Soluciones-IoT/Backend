package com.docseeker.backend.repository;

import com.docseeker.backend.model.Doctor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends ListCrudRepository<Doctor, Integer> {
}
