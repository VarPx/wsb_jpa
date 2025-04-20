package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Nie znaleziono pacjenta lub lekarza.");
        }

        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctorEntity(doctor);
        visit.setTime(time);
        visit.setDescription(description);

        if (patient.getVisits() == null) {
            patient.setVisits(new ArrayList<>());
        }

        patient.getVisits().add(visit);
        entityManager.merge(patient); // kaskadowy zapis wizyty przez pacjenta
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager
                .createQuery("SELECT p FROM PatientEntity p WHERE p.lastName LIKE :param", PatientEntity.class)
                .setParameter("param", "%" + lastName + "%")
                .getResultList();
    }

    // 🔥 Nowa metoda JPQL - Zadanie 2 z Laboratorium 3
    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount) {
        return entityManager.createQuery("""
                SELECT p
                FROM PatientEntity p
                WHERE SIZE(p.visits) > :visitCount
                """, PatientEntity.class)
                .setParameter("visitCount", visitCount)
                .getResultList();
    }
    @Override
    public List<PatientEntity> findPatientsByDoctorId(Long doctorId) {
        return entityManager.createQuery("""
            SELECT DISTINCT p
            FROM PatientEntity p
            JOIN p.visits v
            WHERE v.doctorEntity.id = :doctorId
            """, PatientEntity.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

}
