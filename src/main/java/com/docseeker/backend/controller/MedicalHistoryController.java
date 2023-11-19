package com.docseeker.backend.controller;

import com.docseeker.backend.model.MedicalHistoryRecord;
import com.docseeker.backend.repository.MedicalHistoryRecordRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medical-history")
@CrossOrigin()
public class MedicalHistoryController {
    private final MedicalHistoryRecordRepository repository;

    public MedicalHistoryController(MedicalHistoryRecordRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        MedicalHistoryRecord medicalHistoryRecord = new MedicalHistoryRecord();
        medicalHistoryRecord.setAppointmentId(1);
        medicalHistoryRecord.setDescription("Patient has a cold");
        repository.save(medicalHistoryRecord);
    }

    @GetMapping("")
    public List<MedicalHistoryRecord> findAll() {
        return repository.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<Object[]> getAppointmentsByPatientId(@PathVariable int patientId) {
        return repository.getAppointmentsByPatientId(patientId);
    }
}
