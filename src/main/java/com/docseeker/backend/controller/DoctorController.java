package com.docseeker.backend.controller;

import com.docseeker.backend.model.Doctor;
import com.docseeker.backend.model.DoctorLogInResource;
import com.docseeker.backend.model.UserType;
import com.docseeker.backend.repository.DoctorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Doctor doctor = new Doctor();
        doctor.setName("Na Jaemin");
        doctor.setUserType(UserType.DOCTOR);
        doctor.setEmail("doc_jaemin@gmail.com");
        doctor.setPassword("ilikecats");
        doctor.setDni("12345678A");
        doctor.setAge(25);
        doctor.setSpeciality("Dermatology");
        doctor.setExperienceYears(3);
        doctor.setDescription("I'm a dermatologist with 3 years of experience. I'm specialized in skin diseases.");
        doctor.setPatientsAssisted(100);
        doctor.setDoctorFee(50);
        doctor.setProfilePhoto("https://racdoc.com/uploads/blog/1371_1625856299_doctor-thumb-02.jpg");
        repository.save(doctor);
    }

    @GetMapping("")
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Doctor findById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Doctor doctor) {
        repository.save(doctor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Doctor doctor, @PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
        doctor.setId(id);
        repository.save(doctor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("login")
    public ResponseEntity<List<Doctor>> getDoctorByDNIAndPassword(@RequestBody DoctorLogInResource doctorLoginResource) {
        List<Doctor> doctor = repository
                .findByDniAndPassword(
                        doctorLoginResource.getDni(),
                        doctorLoginResource.getPassword()
                );
        return ResponseEntity.ok(doctor);
    }
}
