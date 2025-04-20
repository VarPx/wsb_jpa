package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.DoctorEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorDao extends Dao<DoctorEntity, Long> {

    List<DoctorEntity> findByMinNumberOfVisits(long numberOfVisits);

    // 🔥 Brakująca metoda – teraz dodana!
    List<DoctorEntity> findByVisitDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);
}
