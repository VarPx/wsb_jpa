package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    void testShouldDeletePatientCascade() {
        // given
        Long patientId = 901L; // <- podmień na istniejący ID z data.sql
        Long doctorId = 100L;  // <- ID lekarza powiązanego z wizytą

        // when
        patientService.deletePatient(patientId);

        // then
        assertThat(patientDao.findOne(patientId)).isNull(); // pacjent usunięty

        // lekarz nadal istnieje
        assertThat(doctorDao.findOne(doctorId)).isNotNull();
    }

    @Test
    void testShouldReturnFullPatientStructure() {
        // given
        Long patientId = 901L;

        // when
        PatientTO patientTO = patientService.findPatientById(patientId);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getVisits()).isNotEmpty();

        var visit = patientTO.getVisits().get(0);
        assertThat(visit.getDoctorFirstName()).isNotNull();
        assertThat(visit.getTreatmentTypes()).isNotNull();


        // Sprawdź własne pole (np. dateOfBirth)
        assertThat(patientTO.getDateOfBirth()).isNotNull();
    }
}
