package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Clinic;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.repositories.ClinicRepository;
import com.example.healthcare_api.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Clinic> getAll(){
        return clinicRepository.findAllWithDepartments();
    }

    public Clinic createClinic(@RequestBody Clinic clinic) {
        if (clinic.getDepartment() != null && clinic.getDepartment().getId() != null) {
            Optional<Department> departmentOptional = departmentRepository.findById(clinic.getDepartment().getId());
            if (departmentOptional.isPresent()) {
                Department department = departmentOptional.get();
                clinic.setDepartment(department);
            } else {
                throw new RuntimeException("Không tìm thấy department với departmentId: " + clinic.getDepartment().getId());
            }
        }
        return clinicRepository.save(clinic);
    }

    public Clinic updateClinic(@PathVariable Long id,@RequestBody Clinic clinic){
        Optional<Clinic> existingClinicOptional = clinicRepository.findById(id);
        if (existingClinicOptional.isPresent()) {
            Clinic existingClinic = existingClinicOptional.get();

            existingClinic.setName(clinic.getName());
            existingClinic.setDepartment(clinic.getDepartment());

            return clinicRepository.save(existingClinic);
        } else {
            return null;
        }
    }

    public void deleteClinic(@PathVariable Long id){
        clinicRepository.deleteById(id);
    }
}
