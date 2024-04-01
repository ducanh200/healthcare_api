package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
}
