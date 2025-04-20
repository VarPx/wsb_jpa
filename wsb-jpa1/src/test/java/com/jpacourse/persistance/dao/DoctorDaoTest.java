package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.DoctorEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DoctorDaoTest {

    @Autowired
    private com.jpacourse.persistance.dao.DoctorDao doctorDao;

    @Test
    void shouldFindDoctorsWithMoreThanXVisits() {
        // given
        long minVisits = 2;

        // when
        List<DoctorEntity> result = doctorDao.findByMinNumberOfVisits(minVisits);

        // then
        assertThat(result).isNotEmpty();
        result.forEach(doc ->
                System.out.println(doc.getFirstName() + " " + doc.getLastName() +
                        " - liczba wizyt: " + doc.getVisitEntities().size()));
        assertThat(result).allMatch(d -> d.getVisitEntities().size() > minVisits);
    }

    @Test
    void shouldFindDoctorsByVisitDateRange() {
        // given
        final String timeFrom = "2024-09-17 17:00";
        final String timeTo = "2024-09-17 19:45";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        final LocalDateTime dateTimeFrom = LocalDateTime.parse(timeFrom, formatter);
        final LocalDateTime dateTimeTo = LocalDateTime.parse(timeTo, formatter);

        // when
        List<DoctorEntity> docs = doctorDao.findByVisitDateRange(dateTimeFrom, dateTimeTo);

        // then
        assertThat(docs).isNotNull();
        assertThat(docs).isNotEmpty();

        docs.forEach(doc ->
                System.out.println(doc.getFirstName() + " " + doc.getLastName()));
    }
}

