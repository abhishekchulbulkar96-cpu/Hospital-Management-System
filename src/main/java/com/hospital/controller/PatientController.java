package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;

import java.util.List;

@RestController   // 🔥 MUST
@RequestMapping("/patients")   // 🔥 MUST
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    @GetMapping
    public List<Patient> getPatients() {
        return service.getAllPatients();
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {

        service.deletePatient(id);

        return "Patient Deleted";
    }
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient){

        return service.updatePatient(id, patient);
    }
}