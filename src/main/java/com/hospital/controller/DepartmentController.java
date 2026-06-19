package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping
    public Department addDepartment(
            @RequestBody Department department) {

        return service.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getDepartments() {
        return service.getAllDepartments();
    }
    
    
    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department){

        return service.updateDepartment(
                id,
                department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(
            @PathVariable Long id){

        service.deleteDepartment(id);

        return "Deleted";
    }
}