package com.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.Patient;
import com.hospital.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;  

    public Patient savePatient(Patient patient) {
        return repo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return repo.findAll();
    }
    public void deletePatient(Long id) {

        repo.deleteById(id);

    }
    public Patient updatePatient(Long id,
            Patient patient){

patient.setPatientId(id);

return repo.save(patient);
}
}