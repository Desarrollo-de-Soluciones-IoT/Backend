package com.docseeker.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient extends User {
    @Column(nullable = true)
    private float height; // in cm
    @Column(nullable = true)
    private float weight; // in kg
    @Column(nullable = true)
    private float bmi; // Body Mass Index
    @Column(nullable = true)
    private String birthDate;
    private String phoneNumber;

    /*@OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    private List<Review> reviews;*/

    @ElementCollection
    @CollectionTable(name = "patient_allergies")
    @Column(name = "allergies", nullable = true)
    private List<String> allergies;
}
