package com.hospital.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq_gen") //genarate the serial id
    
    @SequenceGenerator(
        name = "app_seq_gen",
        sequenceName = "APPOINTMENT_SEQ",
        allocationSize = 1
    )
    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    // 🔥 RELATIONSHIP 1 (Patient)
    @ManyToOne                               //many patient one doctor , departemrnt 
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    // 🔥 RELATIONSHIP 2 (Doctor)
    @ManyToOne                       
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @Column(name = "APPOINTMENT_DATE")
    private LocalDate appointmentDate;
    
    
    @Column(name = "REASON")
	private String reason;
	

    // getters & setters

    public Long getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
}