package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
