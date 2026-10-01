package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.DoctorDTO;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public Doctor addDoctor(@RequestBody DoctorDTO dto) {
        return service.saveDoctor(dto);
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return service.getAllDoctors();
    }
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        service.deleteDoctor(id);

        return "Doctor Deleted";
    }
    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @RequestBody DoctorDTO dto){

        return service.updateDoctor(id, dto);
    }
    
    @GetMapping(
    		"/department/{deptId}"
    		)
    		public List<Doctor>
    		getDoctorsByDepartment(
    		@PathVariable Long deptId){

    		    return service
    		            .getDoctorsByDepartment(
    		                    deptId);
    		}
    
    
    
}