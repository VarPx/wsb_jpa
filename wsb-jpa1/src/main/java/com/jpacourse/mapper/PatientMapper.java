package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.PatientEntity;

import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.mapper.AddressMapper;

public final class PatientMapper {
    private PatientMapper() {} // prywatny konstruktor

    public static PatientTO mapToTO(PatientEntity entity) {
        if (entity == null) return null;

        PatientTO to = new PatientTO();
        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setPatientNumber(entity.getPatientNumber());
        to.setTelephoneNumber(entity.getTelephoneNumber());
        to.setEmail(entity.getEmail());
        to.setDateOfBirth(entity.getDateOfBirth());

        // mapowanie Address
        to.setAddress(AddressMapper.mapToTO(entity.getAddress()));

        // mapowanie listy wizyt
        if (entity.getVisits() != null) {
            to.setVisits(
                    entity.getVisits()
                            .stream()
                            .map(VisitMapper::mapToTO)
                            .toList()
            );
        }

        return to;
    }

    public static PatientEntity mapToEntity(PatientTO to) {
        if (to == null) return null;

        PatientEntity entity = new PatientEntity();
        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());
        entity.setPatientNumber(to.getPatientNumber());
        entity.setTelephoneNumber(to.getTelephoneNumber());
        entity.setEmail(to.getEmail());
        entity.setDateOfBirth(to.getDateOfBirth());

        entity.setAddress(AddressMapper.mapToEntity(to.getAddress()));
        // lista wizyt nie jest mapowana tutaj (bo update kaskadowy z DAO)

        return entity;
    }
}
