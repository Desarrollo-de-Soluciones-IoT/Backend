package com.docseeker.backend.controller;

import com.docseeker.backend.model.Medicine;
import com.docseeker.backend.repository.MedicineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicines")
public class MedicineController {
    private final MedicineRepository repository;

    public MedicineController(MedicineRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Medicine medicine = new Medicine();
        medicine.setName("Paracetamol");
        medicine.setDosage("1-1-1");
        medicine.setDuration("1 week");
        medicine.setDate("2023-10-10");
        Medicine medicine2 = new Medicine();
        medicine2.setName("Antibiotic");
        medicine2.setDosage("3-3-2");
        medicine2.setDuration("3 week");
        medicine2.setDate("2023-07-05");
        repository.save(medicine);
        repository.save(medicine2);
    }

    @GetMapping("")
    public List<Medicine> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Medicine findById(@PathVariable int id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Medicine medicine) {
        repository.save(medicine);
    }
}
