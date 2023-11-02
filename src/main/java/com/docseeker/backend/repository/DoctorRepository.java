package com.docseeker.backend.repository;

import com.docseeker.backend.model.Doctor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends ListCrudRepository<Doctor, Integer> {
    List<Doctor> findByDniAndPassword(String dni, String password);
}
