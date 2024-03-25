package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public DoctorDTO createDoctor(@RequestBody @Valid DoctorDTO request) {
        return doctorService.createDoctor(request);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO request) {
        DoctorDTO updatedDoctor = doctorService.update(id, request);
        return ResponseEntity.ok().body(updatedDoctor);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("id") Long id) {
        DoctorDTO doctorDTO = doctorService.findById(id);
        return ResponseEntity.ok(doctorDTO);
    }
    @GetMapping("/byDepartment/{departmentId}")
    public List<Doctor> getDoctorsByDepartmentId(@PathVariable Long departmentId) {
        return doctorService.getDoctorsByDepartmentId(departmentId);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }
}
