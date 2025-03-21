package com.jpacourse.persistance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// 🔹 Relacja N:1 – Wizyta należy do jednego doktora
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_IDDDD")
	private DoctorEntity doctorEntity; // ✅ Poprawiona nazwa zmiennej

	// 🔹 Relacja N:1 – Wizyta należy do jednego pacjenta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	private PatientEntity patient;

	// 🔹 Relacja 1:N – Jedna wizyta może mieć wiele zabiegów
	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalTreatmentEntity> treatments;

	// 🔹 Gettery i Settery
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctorEntity() { // ✅ Poprawiona nazwa metody
		return doctorEntity;
	}

	public void setDoctorEntity(DoctorEntity doctorEntity) { // ✅ Poprawiona nazwa metody
		this.doctorEntity = doctorEntity;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public List<MedicalTreatmentEntity> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<MedicalTreatmentEntity> treatments) {
		this.treatments = treatments;
	}
}
