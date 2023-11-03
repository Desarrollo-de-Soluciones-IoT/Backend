package com.docseeker.backend.controller;

import com.docseeker.backend.model.*;
import com.docseeker.backend.repository.PatientRepository;
import com.docseeker.backend.util.Util;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
@CrossOrigin()
public class PatientController {
    private final PatientRepository repository;
    public static final String PATIENT_NOT_FOUND = "Patient not found";

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Patient patient = new Patient();
        patient.setName("Jeno");
        patient.setUserType(UserType.PATIENT);
        patient.setEmail("patient_jeno@gmail.com");
        patient.setPassword("ilikecats");
        patient.setDni("32145571E");
        patient.setAge(27);
        patient.setHeight(173);
        patient.setWeight(70);
        patient.setBmi(Util.calculateBMI(patient.getHeight(), patient.getWeight()));
        patient.setBirthDate("1994-03-01");
        patient.setPhoneNumber("991372341");
        patient.setAllergies(List.of("Pollen", "Dust"));
        repository.save(patient);
    }

    @GetMapping("")
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PATIENT_NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Patient patient) {
        patient.setBmi(Util.calculateBMI(patient.getHeight(), patient.getWeight()));
        repository.save(patient);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Patient patient, @PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PATIENT_NOT_FOUND);
        }
        patient.setId(id);
        repository.save(patient);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PATIENT_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    @PostMapping("login")
    public ResponseEntity<List<Patient>> getDoctorByDNIAndPassword(@RequestBody PatientLogInResource patientLogInResource) {
        List<Patient> patient = repository
                .findByDniAndPassword(
                        patientLogInResource.getDni(),
                        patientLogInResource.getPassword()
                );
        return ResponseEntity.ok(patient);
    }
}
