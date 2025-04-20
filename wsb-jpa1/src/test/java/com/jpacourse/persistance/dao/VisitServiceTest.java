package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class VisitServiceTest {

    @Autowired
    private VisitDao visitDao;

    @Test
    void shouldFindVisitsByPatientId() {
        // given — wiemy że pacjent o ID 1 ma 3 wizyty (z data.sql)
        Long patientId = 1L;

        // when
        List<VisitEntity> visits = visitDao.findAllByPatientId(patientId);

        // then
        assertThat(visits).isNotEmpty();
        assertThat(visits).allMatch(v -> v.getPatient().getId().equals(patientId));

        // debug
        visits.forEach(v -> System.out.println(v.getTime() + " - " + v.getDescription()));
    }
}
