package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.BillDTO;
import com.hospital.entity.Bill;
import com.hospital.repository.BillRepository;
import com.hospital.service.BillService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillRepository repo;

    @Autowired
    private BillService service;

    @PostMapping
    public Bill addBill(
            @RequestBody BillDTO dto){

        return service.saveBill(dto);
    }

    @GetMapping
    public List<Bill> getAllBills() {

        return repo.findAll();

    }
    @GetMapping("/billing-page")
    public String billingPage() {
        return "billing";
    }
    
    @DeleteMapping("/{id}")
    public String deleteBill(
            @PathVariable Long id){

        service.deleteBill(id);

        return "Bill Deleted";

    }
    @PutMapping("/{id}")
    public Bill updateBill(
            @PathVariable Long id,
            @RequestBody BillDTO dto){

        return service.updateBill(
                id,
                dto);
    }
    
    @GetMapping("/pdf/{id}")
    public void generatePdf(
            @PathVariable Long id,
            HttpServletResponse response)
            throws Exception {

        service.generateBillPdf(
                id,
                response);

    }
}