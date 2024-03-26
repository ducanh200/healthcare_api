package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.dto.PatientDTO;
import com.example.healthcare_api.entities.Patient;
import com.example.healthcare_api.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping()
    public List<Patient> getAllPatient(){
        return patientService.getAll();
    }

    @PostMapping()
    public Patient createPatient(@RequestBody @Valid PatientDTO request){
        return patientService.createPatient(request);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id,@RequestBody PatientDTO request){
        return patientService.updatePatient(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }

    @GetMapping("/findByName")
    public List<Patient> PatientFindByName(String name){
        return patientService.findByName(name);
    }

    @GetMapping("/{id}")
    public Patient patientById(@PathVariable Long id){
        return patientService.getById(id);
    }
}
