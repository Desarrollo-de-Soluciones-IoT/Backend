package com.docseeker.backend.controller;

import com.docseeker.backend.dto.ReviewDTO;
import com.docseeker.backend.model.Doctor;
import com.docseeker.backend.model.Patient;
import com.docseeker.backend.model.Review;
import com.docseeker.backend.repository.DoctorRepository;
import com.docseeker.backend.repository.PatientRepository;
import com.docseeker.backend.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        review.setAssociatedDoctor(doctor);
        Patient patient = patientRepository.findById(1).orElseThrow();
        review.setCreatedBy(patient);
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
                    review.getAssociatedDoctor().getId(),
                    review.getCreatedBy().getId(),
                    review.getCreatedBy().getName()
            );
            reviewDTOs.add(reviewDTO);
        }

        return reviewDTOs;
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable int id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping("/doctor/{id}")
    public List<ReviewDTO> findByDoctorId(@PathVariable int id) {
        List<Review> reviews = repository.findByAssociatedDoctorId(id);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(
                    review.getId(),
                    review.getDescription(),
                    review.getRating(),
                    review.getAssociatedDoctor().getId(),
                    review.getCreatedBy().getId(),
                    review.getCreatedBy().getName()
            );
            reviewDTOs.add(reviewDTO);
        }

        return reviewDTOs;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());

        Patient patient = patientRepository.findById(reviewDTO.getPatientId()).orElseThrow();
        review.setCreatedBy(patient);

        Doctor doctor = doctorRepository.findById(reviewDTO.getDoctorId()).orElseThrow();
        review.setAssociatedDoctor(doctor);

        repository.save(review);
    }
}
