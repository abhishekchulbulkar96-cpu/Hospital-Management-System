package com.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

   
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/patients-page")
    public String patientsPage() {
        return "patients";
    }

    @GetMapping("/doctors-page")
    public String doctorsPage() {
        return "doctors";
    }

    @GetMapping("/departments-page")
    public String departmentsPage() {
        return "departments";
    }

    @GetMapping("/appointments-page")
    public String appointmentsPage() {
        return "appointments";
    }

    @GetMapping("/records-page")
    public String recordsPage() {
        return "records";
    }

    @GetMapping("/billing-page")
    public String billingPage() {
        return "billing";
    }
    
    
    
    
}