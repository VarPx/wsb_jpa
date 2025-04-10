package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VisitMapper {

    public static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());

        DoctorEntity doctor = visitEntity.getDoctorEntity();
        if (doctor != null) {
            visitTO.setDoctorFirstName(doctor.getFirstName());
            visitTO.setDoctorLastName(doctor.getLastName());
        }

        List<String> treatmentTypes = visitEntity.getTreatments().stream()
                .map(t -> t.getType().name()) // zamienia enum na String
                .collect(Collectors.toList());

        visitTO.setTreatmentTypes(treatmentTypes);

        return visitTO;
    }
}
