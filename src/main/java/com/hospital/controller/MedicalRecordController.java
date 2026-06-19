package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.MedicalRecordDTO;
import com.hospital.entity.MedicalRecord;
import com.hospital.service.MedicalRecordService;

@RestController
@RequestMapping("/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public MedicalRecord addRecord(
            @RequestBody MedicalRecordDTO dto){

        return service.saveRecord(dto);
    }

    @GetMapping
    public List<MedicalRecord> getRecords(){

        return service.getAllRecords();
    }
    
    @PutMapping("/{id}")
    public MedicalRecord updateRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecordDTO dto){

        return service.updateRecord(
                id,
                dto);
    }

    @DeleteMapping("/{id}")
    public String deleteRecord(
            @PathVariable Long id){

        service.deleteRecord(id);

        return "Deleted";
    }
}