package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.repositories.DoctorRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        return doctorRepositoty.findById(id).map(d->{
            d.setName(doctor.getName());
            d.setThumbnail(doctor.getThumbnail());
            d.setPhonenumber(doctor.getPhonenumber());
            d.setClinicId(doctor.getClinicId());
            d.setDepartmentId(doctor.getDepartmentId());
            d.setUserId(doctor.getUserId());
            return doctorRepositoty.save(doctor);
        }).orElseGet(()->{
            doctor.setId(id);
            return doctorRepositoty.save(doctor);
        });
    }

    public List<Doctor> searchDoctor(String name){
        return doctorRepositoty.findAllByNameContaining(name);
    }

}
