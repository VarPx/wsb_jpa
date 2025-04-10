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
	@ManyToOne
	@JoinColumn(name = "visit_id", nullable = false)
	private VisitEntity visit;

	// --- ✅ Gettery i settery ---
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TreatmentType getType() {
		return type;
	}

	public void setType(TreatmentType type) {
		this.type = type;
	}

	public VisitEntity getVisit() {
		return visit;
	}

	public void setVisit(VisitEntity visit) {
		this.visit = visit;
	}
}
