package com.docseeker.backend.controller;

import com.docseeker.backend.model.*;
import com.docseeker.backend.repository.PrescriptionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/prescription")
public class PrescriptionController {

    private final PrescriptionRepository repository;

    public PrescriptionController(PrescriptionRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(1);
        prescription.setDoctorId(1);
        List<Integer> medicines = new ArrayList<>();
        medicines.add(1);
        medicines.add(2);
        prescription.setMedicines(medicines);
        repository.save(prescription);
    }

    @GetMapping("")
    public List<Prescription> findAll() {
        return repository.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable int patientId) {
        return repository.getPrescriptionsByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getPrescriptionByDoctorId(@PathVariable int doctorId) {
        return repository.getPrescriptionByDoctorId(doctorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Prescription prescription) {
        repository.save(prescription);
    }

    @PostMapping("/{prescriptionId}/medicine/{medicineId}")
    public void addMedicine(@PathVariable int prescriptionId, @PathVariable int medicineId) {
        Prescription prescription = repository.findById(prescriptionId).orElseThrow();
        List<Integer> medicines = prescription.getMedicines();
        medicines.add(medicineId);
        prescription.setMedicines(medicines);
        repository.save(prescription);
    }
}