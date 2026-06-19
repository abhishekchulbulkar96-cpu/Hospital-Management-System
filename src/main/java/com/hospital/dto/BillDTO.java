package com.hospital.dto;

public class BillDTO {

    private Long patientId;

    private Double consultationFee;

    private Double medicineFee;

    private String billDate;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

    // getters setters
    
    
}