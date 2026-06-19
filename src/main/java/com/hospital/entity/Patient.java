package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq_gen")
    
    @SequenceGenerator(
        name = "patient_seq_gen",
        sequenceName = "PATIENT_SEQ",
        allocationSize = 1
    )
    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE")
    private String phone;

    // getters & setters
}