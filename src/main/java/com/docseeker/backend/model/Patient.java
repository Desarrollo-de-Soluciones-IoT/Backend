package com.docseeker.backend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
public class Patient extends User {
    @OneToMany(mappedBy = "patient", cascade = CascadeType.DETACH)
    private List<Appointment> appointments;
}
