package com.docseeker.backend.controller;

import com.docseeker.backend.dto.ReviewDTO;
import com.docseeker.backend.model.Doctor;
import com.docseeker.backend.model.Patient;
import com.docseeker.backend.model.Review;
import com.docseeker.backend.repository.DoctorRepository;
import com.docseeker.backend.repository.PatientRepository;
import com.docseeker.backend.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewRepository repository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public ReviewController(ReviewRepository repository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    private void init() {
        Review review = new Review();
        review.setDescription("Great doctor. Totally recommend him!");
        review.setRating(5);
        Doctor doctor = doctorRepository.findById(1).orElseThrow();
        review.setDoctor(doctor);
        Patient patient = patientRepository.findById(1).orElseThrow();
        review.setPatient(patient);
        repository.save(review);
    }

    @GetMapping("")
    public List<ReviewDTO> findAll() {
        List<Review> reviews = repository.findAll();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(
                    review.getId(),
                    review.getDescription(),
                    review.getRating(),
                    review.getPatient().getId(),
                    review.getDoctor().getId()
            );
            reviewDTOs.add(reviewDTO);
        }

        return reviewDTOs;
    }

    @GetMapping("/{id}")
    public Review findById(int id) {
        return repository.findById(id).orElseThrow();
    }
}
