package com.docseeker.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "patients")
@AllArgsConstructor
public class Patient extends User {
    public Patient(int id, UserType userType, String email, String password, String dni, int age) {
        super(id, userType, email, password, dni, age);
    }
}
