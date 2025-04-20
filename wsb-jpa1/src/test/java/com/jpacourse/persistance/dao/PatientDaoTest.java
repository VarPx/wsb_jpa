package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PatientDaoTest {

    @Autowired
    private com.jpacourse.persistance.dao.PatientDao patientDao;

    @Test
    void shouldAddVisitToPatient() {
        Long patientId = 1L; // Adam Malinowski
        Long doctorId = 1L;  // Anna Nowak
        LocalDateTime time = LocalDateTime.of(2024, 4, 1, 10, 0);
        String description = "Badanie kontrolne";

        patientDao.addVisitToPatient(patientId, doctorId, time, description);
        PatientEntity patient = patientDao.findOne(patientId);

        assertThat(patient.getVisits()).isNotEmpty();

        boolean found = patient.getVisits().stream()
                .anyMatch(v -> v.getDescription().equals(description) &&
                        v.getDoctorEntity().getId().equals(doctorId));
        assertThat(found).isTrue();
    }

    @Test
    void shouldFindPatientsByLastName() {
        List<PatientEntity> results = patientDao.findByLastName("Nowak");

        assertThat(results).isNotEmpty();
        assertThat(results).allMatch(p -> p.getLastName().contains("Nowak"));
    }

    @Test
    void shouldFindPatientsWithMoreThanXVisits() {
        // given – wiemy z data.sql że pacjent o ID 4, 5, 6 mają odpowiednio 4, 5, 6 wizyt
        int minVisitCount = 4;

        // when
        List<PatientEntity> results = patientDao.findPatientsWithMoreThanXVisits(minVisitCount);

        // then
        assertThat(results).isNotEmpty();
        assertThat(results)
                .allMatch(p -> p.getVisits().size() > minVisitCount);

        // dodatkowo możesz wypisać pacjentów (do debugowania):
        results.forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName() + " - " + p.getVisits().size() + " wizyt"));
    }
    @Test
    void shouldFindPatientsByDoctorId() {
        // given – z data.sql: doktor 1 (Anna Nowak) ma kilku pacjentów
        Long doctorId = 1L;

        // when
        List<PatientEntity> results = patientDao.findPatientsByDoctorId(doctorId);

        // then
        assertThat(results).isNotEmpty();
        results.forEach(p -> {
            boolean hasVisitWithDoctor = p.getVisits().stream()
                    .anyMatch(v -> v.getDoctorEntity().getId().equals(doctorId));
            assertThat(hasVisitWithDoctor).isTrue();
            System.out.println(p.getFirstName() + " " + p.getLastName() + " - ma wizytę u lekarza ID: " + doctorId);
        });
    }

}
