package com.example.healthcare_api.controllers;

import com.example.healthcare_api.entities.Clinic;
import com.example.healthcare_api.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clinics")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @GetMapping()
    public List<Clinic> getAllClinic(){
        return clinicService.getAll();
    }

    @PostMapping()
    public Clinic createClinic(@RequestBody Clinic clinic){
        return clinicService.createClinic(clinic);
    }

    @PutMapping("/{id}")
    public Clinic updateClinic(@PathVariable Long id,@RequestBody Clinic clinic){
        return clinicService.updateClinic(id,clinic);
    }

    @DeleteMapping("/{id}")
    public void deleteClinic(@PathVariable Long id){
        clinicService.deleteClinic(id);
    }
}
