package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    void shouldAddVisitToPatient() {
        // given
        Long patientId = 901L;
        Long doctorId = 100L;
        LocalDateTime time = LocalDateTime.now();
        String description = "Badanie kontrolne";

        // when
        patientDao.addVisitToPatient(patientId, doctorId, time, description);

        // then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient.getVisits()).isNotEmpty();

        VisitEntity visit = patient.getVisits().get(0);
        assertThat(visit.getDescription()).isEqualTo("Badanie kontrolne");
        assertThat(visit.getDoctorEntity().getId()).isEqualTo(doctorId);
    }
}
