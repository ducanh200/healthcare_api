package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends JpaRepository<Booking,Long> {

}
