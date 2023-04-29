package com.docseeker.backend.model;

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
    private int height;
    private int weight;
    private Date birthDate;
    private String phoneNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.DETACH)
    private List<Appointment> appointments;
}
