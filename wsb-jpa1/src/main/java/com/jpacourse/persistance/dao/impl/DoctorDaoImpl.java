package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DoctorEntity> findByMinNumberOfVisits(long numberOfVisits) {
        return entityManager.createQuery("""
                SELECT doc FROM DoctorEntity doc
                WHERE SIZE(doc.visitEntities) > :param1
                """, DoctorEntity.class)
                .setParameter("param1", numberOfVisits)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> findByVisitDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return entityManager.createQuery("""
                SELECT DISTINCT doc FROM DoctorEntity doc
                JOIN doc.visitEntities visit
                WHERE visit.time BETWEEN :param1 AND :param2
                """, DoctorEntity.class)
                .setParameter("param1", dateFrom)
                .setParameter("param2", dateTo)
                .getResultList();
    }
}
