package com.example.healthcare_api.repositories;

import com.example.healthcare_api.dto.ClinicDTO;
import com.example.healthcare_api.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    @Query("SELECT c FROM Clinic c LEFT JOIN FETCH c.department")
    List<Clinic> findAllWithDepartments();
}
