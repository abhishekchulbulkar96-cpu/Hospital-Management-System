package com.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.DoctorDTO;
import com.hospital.entity.Department;
import com.hospital.entity.Doctor;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DepartmentRepository;
import com.hospital.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    @Autowired
    private DepartmentRepository departmentRepo;

    public Doctor saveDoctor(DoctorDTO dto) {

        Department department = departmentRepo
                .findById(dto.getDeptId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDepartment(department);

        return repo.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }
    public void deleteDoctor(Long id) {

        repo.deleteById(id);

    }
    
    
    public Doctor updateDoctor(Long id, DoctorDTO dto) {

        Doctor doctor =
                repo.findById(id).orElseThrow();

        doctor.setName(dto.getName());

        doctor.setSpecialization(
                dto.getSpecialization());

        Department dept =
                departmentRepo.findById(
                        dto.getDeptId())
                        .orElseThrow();

        doctor.setDepartment(dept);

        return repo.save(doctor);
    }
    
}