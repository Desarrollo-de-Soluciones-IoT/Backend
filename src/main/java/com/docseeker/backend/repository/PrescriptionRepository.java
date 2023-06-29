package com.docseeker.backend.repository;

import com.docseeker.backend.model.Prescription;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PrescriptionRepository extends ListCrudRepository<Prescription, Integer> {
    List<Prescription> getPrescriptionsByPatientId(int patientId);
    List<Prescription> getPrescriptionByDoctorId(int doctorId);
}
