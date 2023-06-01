package com.docseeker.backend.repository;

import com.docseeker.backend.model.Review;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends ListCrudRepository<Review, Integer> {
    List<Review> findByAssociatedDoctorId(int id);
}
