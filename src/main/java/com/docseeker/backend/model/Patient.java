package com.docseeker.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient extends User {
    private float height; // in cm
    private float weight; // in kg
    private float bmi; // Body Mass Index
    private Date birthDate;
    private String phoneNumber;

    @OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    private List<Review> reviews;

    @ElementCollection
    @CollectionTable(name = "patient_allergies")
    @Column(name = "allergies")
    private List<String> allergies;

    @ElementCollection
    @CollectionTable(name = "patient_diseases")
    @Column(name = "diseases")
    private List<String> diseases;
}
