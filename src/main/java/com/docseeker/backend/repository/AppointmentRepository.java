package com.docseeker.backend.repository;

import com.docseeker.backend.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends ListCrudRepository<Appointment, Integer> {
    @Query("SELECT a, d, p FROM Appointment a LEFT JOIN Doctor d ON a.doctorId = d.id LEFT JOIN Patient p ON a.patientId = p.id WHERE d.id = :doctorId AND p.id = :patientId")
    List<Object[]> getAppointmentsByDoctorAndPatientId(@Param("doctorId") int doctorId, @Param("patientId") int patientId);

    List<Object> getAppointmentsByPatientId(int patientId);

    List<Object> getAppointmentsByDoctorId(int doctorId);
}
