package com.jpacourse.persistance.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	// 🔹 Relacja 1:1 Pacjent ma jeden adres
	// 🔹 Relacja jednostronna od strony dziecka (Patient) do rodzica (Address)
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity address;

	// 🔹 Relacja 1:N Pacjent może mieć wiele wizyt
	// 🔹 Relacja jednostronna od strony dziecka (Visit) do rodzica (Patient)
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> visits;
}
