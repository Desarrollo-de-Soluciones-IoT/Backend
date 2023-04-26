package com.docseeker.backend.controller;

import com.docseeker.backend.model.Appointment;
import com.docseeker.backend.repository.AppointmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    private final AppointmentRepository repository;

    public AppointmentController(AppointmentRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Appointment appointment = new Appointment();
        appointment.setDateTime(LocalDateTime.now());
        appointment.setDescription("I have a headache");
        repository.save(appointment);
    }

    @GetMapping("")
    public List<Appointment> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment findById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Appointment appointment) {
        repository.save(appointment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Appointment appointment, @PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
        }
        appointment.setId(id);
        repository.save(appointment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}