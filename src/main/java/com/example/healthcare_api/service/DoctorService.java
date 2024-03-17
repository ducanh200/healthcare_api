package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.repositories.DoctorRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepositoty doctorRepositoty;

    public List<Doctor> getAll(){
        return doctorRepositoty.findAll();
    }

    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorRepositoty.save(doctor);
    }


    public List<Doctor> searchDoctor(String name){
        return doctorRepositoty.findAllByNameContaining(name);
    }

    public Doctor getById(@PathVariable Long id){
        Optional<Doctor> doctorOptional = doctorRepositoty.findById(id);
        try {
            return doctorOptional.get();
        }catch (Exception e){
            return null;
        }
    }
}
