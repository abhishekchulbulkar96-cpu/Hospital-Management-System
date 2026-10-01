package com.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.AppointmentDTO;
import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.entity.Doctor;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.DoctorRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    // 🔥 STEP 3 (MAIN LOGIC)
    public Appointment saveAppointment(AppointmentDTO dto) {

        // 1. Fetch Patient
        Patient patient = patientRepo.findById(dto.getPatientId())
        		.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        // 2. Fetch Doctor
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
        		.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // 3. Convert DTO → Entity
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(LocalDate.parse(dto.getAppointmentDate()));
        appointment.setReason(
                dto.getReason());

        // 4. Save to DB
        return repo.save(appointment);
    }

    // GET API
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }
    public void deleteAppointment(Long id){

        repo.deleteById(id);

    }
    
    public Appointment updateAppointment(
            Long id,
            AppointmentDTO dto){

        Appointment appointment =
                repo.findById(id)
                    .orElseThrow();

        Patient patient =
                patientRepo.findById(
                        dto.getPatientId())
                        .orElseThrow();

        Doctor doctor =
                doctorRepo.findById(
                        dto.getDoctorId())
                        .orElseThrow();

        appointment.setPatient(patient);

        appointment.setDoctor(doctor);
       

        appointment.setAppointmentDate(
                LocalDate.parse(
                        dto.getAppointmentDate()));
        
        appointment.setReason(dto.getReason());

        return repo.save(appointment);
        
       
        
        
        
    }
    
    
   
    public Appointment getAppointmentById(
            Long id){

        return repo.findById(id)
                .orElseThrow();
    }
    
    
}