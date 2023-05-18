package com.docseeker.backend.controller;

import com.docseeker.backend.model.Doctor;
import com.docseeker.backend.model.Review;
import com.docseeker.backend.repository.DoctorRepository;
import com.docseeker.backend.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewRepository repository;
    private final DoctorRepository doctorRepository;

    public ReviewController(ReviewRepository repository, DoctorRepository doctorRepository) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
    }

    @PostConstruct
    private void init() {
        Review review = new Review();
        review.setDescription("Great doctor. Totally recommend him!");
        review.setRating(5);
        Doctor doctor = doctorRepository.findById(1).orElseThrow();
        review.setDoctor(doctor);
        repository.save(review);
    }

    @GetMapping("")
    public List<Review> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Review findById(int id) {
        return repository.findById(id).orElseThrow();
    }
}
