package com.jpacourse.service.impl;

import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.service.PatientService;
import com.jpacourse.dto.PatientTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.jpacourse.dto.PatientTO;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findPatientById(Long id) {
        return PatientMapper.mapToTO(patientDao.findOne(id));
    }

    @Override
    public void deletePatient(Long id) {
        patientDao.delete(id);
    }
}
