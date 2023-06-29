package com.docseeker.backend.repository;

import com.docseeker.backend.model.Medicine;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MedicineRepository extends ListCrudRepository<Medicine, Integer> {
    List<Medicine> findByPrescriptionId(int prescriptionId);
}
