package com.hospital.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.MedicalRecordDTO;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.PatientRepository;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    public MedicalRecord saveRecord(
            MedicalRecordDTO dto){

        Patient patient =
                patientRepo.findById(
                        dto.getPatientId())
                        .orElseThrow();

        MedicalRecord record =
                new MedicalRecord();

        record.setPatient(patient);

        record.setDiagnosis(
                dto.getDiagnosis());

        record.setTreatment(
                dto.getTreatment());

        record.setPrescription(
                dto.getPrescription());

        record.setVisitDate(
                LocalDate.parse(
                        dto.getVisitDate()));

        return repo.save(record);
    }

    public List<MedicalRecord> getAllRecords(){

        return repo.findAll();
    }
    
    
    public MedicalRecord updateRecord(
            Long id,
            MedicalRecordDTO dto){

        MedicalRecord record =
                repo.findById(id)
                    .orElseThrow();

        Patient patient =
                patientRepo.findById(
                        dto.getPatientId())
                        .orElseThrow();

        record.setPatient(patient);

        record.setDiagnosis(
                dto.getDiagnosis());

        record.setTreatment(
                dto.getTreatment());

        record.setPrescription(
                dto.getPrescription());

        record.setVisitDate(
                LocalDate.parse(
                        dto.getVisitDate()));

        return repo.save(record);
    }

    public void deleteRecord(
            Long id){

        repo.deleteById(id);
    }
}