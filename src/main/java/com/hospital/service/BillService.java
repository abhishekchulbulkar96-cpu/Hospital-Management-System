package com.hospital.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.BillDTO;
import com.hospital.entity.Appointment;
import com.hospital.entity.Bill;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.BillRepository;
import com.hospital.repository.PatientRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class BillService {

	@Autowired
	private BillRepository repo;

	@Autowired
	private PatientRepository patientRepo;

	public Bill saveBill(
	        BillDTO dto){

	    Patient patient =
	            patientRepo.findById(
	                    dto.getPatientId())
	                    .orElseThrow();

	    Bill bill =
	            new Bill();

	    bill.setPatient(patient);

	    bill.setConsultationFee(
	            dto.getConsultationFee());

	    bill.setMedicineFee(
	            dto.getMedicineFee());

	    bill.setTotalAmount(
	            dto.getConsultationFee()
	            +
	            dto.getMedicineFee());

	    bill.setBillDate(
	            LocalDate.parse(
	                    dto.getBillDate()));

	    return repo.save(bill);
	}
	
	public void deleteBill(Long id){

	    repo.deleteById(id);

	}
	
	public Bill updateBill(
	        Long id,
	        BillDTO dto){

	    Bill bill =
	            repo.findById(id)
	                .orElseThrow();

	    Patient patient =
	            patientRepo.findById(
	                    dto.getPatientId())
	                    .orElseThrow();

	    bill.setPatient(patient);

	    bill.setConsultationFee(
	            dto.getConsultationFee());

	    bill.setMedicineFee(
	            dto.getMedicineFee());

	    bill.setTotalAmount(
	            dto.getConsultationFee()
	            +
	            dto.getMedicineFee());

	    bill.setBillDate(
	            LocalDate.parse(
	                    dto.getBillDate()));

	    return repo.save(bill);
	}
	
	@Autowired
	private PdfService pdfService;

	public void generateBillPdf(
	        Long id,
	        HttpServletResponse response)
	        throws Exception {

	    Bill bill =
	            repo.findById(id)
	                .orElseThrow();

	    pdfService.generateBillPdf(
	            response,
	            bill);

	}
}