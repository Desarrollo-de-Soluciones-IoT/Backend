package com.docseeker.backend.repository;

import com.docseeker.backend.model.Review;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ListCrudRepository<Review, Integer> {
}
