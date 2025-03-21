package com.jpacourse.persistance.entity;

import jakarta.persistence.*;
import com.jpacourse.persistance.enums.TreatmentType;

@Entity
@Table(name = "MEDICAL_TREATMENT")
public class MedicalTreatmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TreatmentType type;

	// 🔹 Relacja N:1 – Jeden zabieg należy do jednej wizyty
	// 🔹 W tabeli MEDICAL_TREATMENT znajduje się klucz obcy visit_id, który wskazuje na VISIT(id)
	@ManyToOne
	@JoinColumn(name = "visit_id", nullable = false)
	private VisitEntity visit;
}
