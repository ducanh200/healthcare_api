package com.example.healthcare_api.controllers;

import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping()
    public List<Doctor> getAllDoctor(){
        return doctorService.getAll();
    }

    @PostMapping()
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }


    @GetMapping("/search")
    public List<Doctor> searchDoctorByname(String name){
        return doctorService.searchDoctor(name);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        return doctorService.getById(id);
    }
}
