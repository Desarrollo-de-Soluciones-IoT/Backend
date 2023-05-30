package com.docseeker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medical_history_records")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private int appointmentId;
}
