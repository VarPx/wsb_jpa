package com.jpacourse.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String city;
	private String addressLine1;
	private String addressLine2;
	private String postalCode;

	// 🔹 Relacja 1:1 – Jeden adres może należeć tylko do jednego pacjenta
	// 🔹 `mappedBy = "address"` oznacza, że klucz obcy `address_id` znajduje się w tabeli `PATIENT`
	@OneToOne(mappedBy = "address")
	private PatientEntity patient;

	// 🔹 GETTERY i SETTERY
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
}
