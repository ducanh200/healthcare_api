package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
    @Query("SELECT s FROM Shift s WHERE s.id NOT IN (" +
            "SELECT b.shift.id FROM Booking b WHERE b.date = :date AND b.department.id = :departmentId " +
            "GROUP BY b.shift.id HAVING COUNT(b.id) >= (SELECT d.maxBooking FROM Department d WHERE d.id = :departmentId))")
    List<Shift> findAvailableShiftsByDateAndDepartment(@Param("date") Date date, @Param("departmentId") Long departmentId);
}
