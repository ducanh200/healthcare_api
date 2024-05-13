package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByPatientId(Long id);

    List<Booking> findByDepartmentId(Long id);
}
