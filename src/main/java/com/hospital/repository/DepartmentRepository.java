package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hospital.entity.Department;
import com.lowagie.text.List;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
	

}