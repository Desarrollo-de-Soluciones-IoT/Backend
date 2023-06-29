package com.docseeker.backend.repository;

import com.docseeker.backend.model.Medicine;
import org.springframework.data.repository.ListCrudRepository;

public interface MedicineRepository extends ListCrudRepository<Medicine, Integer> {
}
