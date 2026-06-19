package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.AppointmentDTO;
import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.service.PdfService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;
    
    @Autowired
    private PdfService pdfService;

    @PostMapping
    public Appointment addAppointment(@RequestBody AppointmentDTO dto) {
        return service.saveAppointment(dto);
    }

    @GetMapping
    public List<Appointment> getAppointments() {
        return service.getAllAppointments();
    }
    
    @DeleteMapping("/{id}")
    public String deleteAppointment(
            @PathVariable Long id){

        service.deleteAppointment(id);

        return "Appointment Deleted";
    }
    
    @PutMapping("/{id}")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDTO dto){

        return service.updateAppointment(
                id,
                dto);
    }
    
    @GetMapping("/pdf/{id}")
    public void generatePdf(
            @PathVariable Long id,
            HttpServletResponse response)
            throws Exception {

        Appointment appointment =
                service.getAppointmentById(id);

        response.setContentType(
                "application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=appointment.pdf");

        pdfService.generateAppointmentSlip(

                response,

                appointment.getPatient().getName(),

                appointment.getDoctor().getName(),

                appointment.getAppointmentDate()
                        .toString()

        );
    }
}