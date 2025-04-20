package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description);

    List<PatientEntity> findByLastName(String lastName);

    // 🔥 Nowa metoda JPQL – Zadanie 2 z Lab 3
    List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount);

    // 🔥 Nowa metoda JPQL – Zadanie 3 z Lab 3
    List<PatientEntity> findPatientsByDoctorId(Long doctorId);
}
