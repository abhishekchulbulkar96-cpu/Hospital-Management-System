package com.hospital.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCTOR")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq_gen")
    
    @SequenceGenerator(
        name = "doctor_seq_gen",
        sequenceName = "DOCTOR_SEQ",
        allocationSize = 1
    )
    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "NAME")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department department;

    @Column(name = "SPECIALIZATION")
    private String specialization;

    // 🔥 IMPORTANT (getters & setters)

    public Long getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}