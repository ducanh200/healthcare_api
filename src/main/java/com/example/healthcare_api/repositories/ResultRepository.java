package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    @Query("SELECT r FROM Result r JOIN r.booking b WHERE b.status = 3")
    List<Result> findResultsByBookingStatus();

    List<Result> findByBookingId(Long bookingId);

}
