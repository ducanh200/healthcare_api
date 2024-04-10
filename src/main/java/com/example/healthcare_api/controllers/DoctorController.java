package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping()
    public List<DoctorDTO> getAllDoctor(){
        return doctorService.getAll();
    }

    @PostMapping()
    public DoctorDTO createDoctor(@RequestBody @Valid DoctorDTO request) {
        return doctorService.createDoctor(request);
    }


    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO request) {
        return doctorService.update(id, request);
    }

    @PutMapping("/changePasswordById/{id}")
    public DoctorDTO changePassword(@PathVariable Long id, @RequestBody DoctorDTO request) {
        return doctorService.changePassword(id, request);
    }


    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id){
        return doctorService.findById(id);
    }

    @GetMapping("/departmentId/{departmentId}")
    public ResponseEntity<List<DoctorDTO>> getByDepartmentId(@PathVariable Long departmentId) {
        List<DoctorDTO> doctorDTOs = doctorService.getByDepartmentId(departmentId);
        return ResponseEntity.ok().body(doctorDTOs);
    }
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }
}
