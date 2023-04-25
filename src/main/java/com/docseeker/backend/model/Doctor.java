package com.docseeker.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {
    private String speciality;
    private int experienceYears;

    public Doctor(int id, UserType userType, String email, String password, String dni, int age, String speciality, int experienceYears) {
        super(id, userType, email, password, dni, age);
        this.speciality = speciality;
        this.experienceYears = experienceYears;
    }
}
