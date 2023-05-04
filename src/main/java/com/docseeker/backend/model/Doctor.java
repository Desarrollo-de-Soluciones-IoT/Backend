package com.docseeker.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne(mappedBy = "doctor")
    private Appointment appointment;
}
