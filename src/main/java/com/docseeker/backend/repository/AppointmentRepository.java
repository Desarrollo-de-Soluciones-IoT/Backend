package com.docseeker.backend.repository;

import com.docseeker.backend.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends ListCrudRepository<Appointment, Integer> {
    @Query("SELECT a, d FROM Appointment a LEFT JOIN Doctor d ON a.doctorId = d.id WHERE d.id = :doctorId")
    List<Object[]> customGetAppointmentsByDoctorId(@Param("doctorId") int doctorId);
}
