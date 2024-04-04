package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.PrescriptionDTO;
import com.example.healthcare_api.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping()
    public List<PrescriptionDTO> getAll(){
        return prescriptionService.getAllPrescriptions();
    }

    @PostMapping()
    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO request){
        return prescriptionService.createPrescription(request);
    }
}
