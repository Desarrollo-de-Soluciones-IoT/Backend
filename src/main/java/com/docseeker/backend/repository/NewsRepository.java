package com.docseeker.backend.repository;

import com.docseeker.backend.model.News;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends ListCrudRepository<News, Integer> {
}
