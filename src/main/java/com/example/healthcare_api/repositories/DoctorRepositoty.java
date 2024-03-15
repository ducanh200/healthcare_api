package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepositoty extends JpaRepository<Doctor,Long> {
    List<Doctor> findAllByNameContaining(String name);

}
