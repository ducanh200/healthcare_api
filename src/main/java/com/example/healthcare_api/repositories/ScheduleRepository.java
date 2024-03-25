package com.example.healthcare_api.repositories;

import com.example.healthcare_api.dto.ScheduleDTO;
import com.example.healthcare_api.entities.Device;
import com.example.healthcare_api.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.clinic")
    List<Schedule> findAllWithClinic();
}
