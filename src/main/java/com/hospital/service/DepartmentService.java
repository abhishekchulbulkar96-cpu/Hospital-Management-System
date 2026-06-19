package com.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.Department;
import com.hospital.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public Department saveDepartment(Department department) {
        return repo.save(department);
    }

    public List<Department> getAllDepartments() {
        return repo.findAll();
    }
    
    public Department updateDepartment(
            Long id,
            Department department){

        Department existing =
                repo.findById(id)
                    .orElseThrow();

        existing.setDeptName(
                department.getDeptName());

        return repo.save(existing);
    }

    public void deleteDepartment(
            Long id){

        repo.deleteById(id);
    }
}