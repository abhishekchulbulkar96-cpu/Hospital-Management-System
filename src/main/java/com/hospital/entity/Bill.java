package com.hospital.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="BILL")
public class Bill {
	@Id
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "bill_seq_gen")
	@SequenceGenerator(
	        name="bill_seq_gen",
	        sequenceName="BILL_SEQ",
	        allocationSize=1)
	private Long billId;

	@ManyToOne
	@JoinColumn(name="PATIENT_ID")
	private Patient patient;

	@Column(name="CONSULTATION_FEE")
	private Double consultationFee;

	@Column(name="MEDICINE_FEE")
	private Double medicineFee;

	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;

	@Column(name="BILL_DATE")
	private LocalDate billDate;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(Double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public Double getMedicineFee() {
		return medicineFee;
	}

	public void setMedicineFee(Double medicineFee) {
		this.medicineFee = medicineFee;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}
	
	
	
}
