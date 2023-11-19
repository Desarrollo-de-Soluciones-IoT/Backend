package com.docseeker.backend.controller;

import com.docseeker.backend.model.Appointment;
import com.docseeker.backend.model.Patient;
import com.docseeker.backend.repository.AppointmentRepository;
import com.docseeker.backend.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("api/v1/appointments")
@CrossOrigin()
public class AppointmentController {

    private final AppointmentRepository repository;
    private final PatientRepository patientRepository;

    public AppointmentController(AppointmentRepository repository, PatientRepository patientRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    private void init() {
        Appointment appointment = new Appointment();
        appointment.setDate("2021-10-10");
        appointment.setStartTime("10:00");
        appointment.setEndTime("11:00");
        appointment.setDoctorId(1);
        appointment.setPatientId(1);
        repository.save(appointment);
    }

    @GetMapping("")
    public List<Appointment> findAll() {
        return repository.findAll();
    }

    @GetMapping("/doctor/{doctorId}/patient/{patientId}")
    public List<Object[]> findAllAppointmentsByDoctorAndPatientId(@PathVariable int doctorId, @PathVariable int patientId) {
        return repository.getAppointmentsByDoctorAndPatientId(doctorId, patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> findAllAppointmentsByDoctorId(@PathVariable int doctorId) {
        return repository.getAppointmentsByDoctorId(doctorId);
    }

    @GetMapping("/doctor/{doctorId}/date/{date}")
    public List<Object> findAllAppointmentsByDoctorIdAndDate(@PathVariable int doctorId, @PathVariable String date) {
        return repository.getAppointmentsByDoctorIdAndAndDate(doctorId, date);
    }

    @GetMapping("patient/{patientId}")
    public List<Object> findAllAppointmentsByPatientId(@PathVariable int patientId) {
        return repository.getAppointmentsByPatientId(patientId);
    }

    @GetMapping("doctor/{doctorId}/patients")
    public List<Optional<Patient>> findAllPatientsByDoctorId(@PathVariable int doctorId) {
        List<Appointment> doctorsAppointments = repository.getAppointmentsByDoctorId(doctorId);
        List<Integer> patientsIds = new ArrayList<>();

        for (Appointment appointment : doctorsAppointments) {
            patientsIds.add(appointment.getPatientId());
        }

        Set<Integer> patientsIdsSet = new HashSet<>(patientsIds);
        patientsIds.clear();
        patientsIds.addAll(patientsIdsSet);

        List<Optional<Patient>> patients = new ArrayList<>();

        for (Integer id : patientsIds) {
            Optional<Patient> patient = patientRepository.findById(id);
            patients.add(patient);
        }

        return patients;
    }

    @GetMapping("/{id}")
    public Appointment findById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = "application/json")
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
