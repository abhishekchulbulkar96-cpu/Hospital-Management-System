package com.hospital.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "MEDICAL_RECORD")
public class MedicalRecord {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "record_seq_gen")
    @SequenceGenerator(
            name = "record_seq_gen",
            sequenceName = "MEDICAL_RECORD_SEQ",
            allocationSize = 1)
    @Column(name = "RECORD_ID")
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @Column(name = "DIAGNOSIS")
    private String diagnosis;

    @Column(name = "TREATMENT")
    private String treatment;

    @Column(name = "PRESCRIPTION")
    private String prescription;

    @Column(name = "VISIT_DATE")
    private LocalDate visitDate;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

    // Getters and Setters
    
    
}