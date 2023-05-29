package com.docseeker.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor extends User {
    private String speciality;
    private int experienceYears;
    private String description;
    private int patientsAssisted;
    private float doctorFee;
    private String profilePhoto;

    @OneToMany(mappedBy = "associatedDoctor", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews;
}
