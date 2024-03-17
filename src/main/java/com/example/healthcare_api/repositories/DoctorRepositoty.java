package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepositoty extends JpaRepository<Doctor,Long> {
    List<Doctor> findAllByNameContaining(String name);

    Optional<Doctor> findById(Long id);

}
