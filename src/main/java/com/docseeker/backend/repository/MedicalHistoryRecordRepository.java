package com.docseeker.backend.repository;

import com.docseeker.backend.model.MedicalHistoryRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalHistoryRecordRepository extends ListCrudRepository<MedicalHistoryRecord, Integer> {
    @Query("SELECT a, m, p, d FROM Appointment a LEFT JOIN MedicalHistoryRecord m ON a.id = m.appointmentId LEFT JOIN Patient p ON a.patientId = p.id LEFT JOIN Doctor d ON d.id =a.doctorId WHERE p.id = :patientId")
    List<Object[]> getAppointmentsByPatientId(@Param("patientId") int patientId);
}
