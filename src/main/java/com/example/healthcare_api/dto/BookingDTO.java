package com.example.healthcare_api.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class BookingDTO {
    private Long id;
    private Date bookingAt;
    private Integer status;
    private Long scheduleId;
    private Long patientId;
    private ScheduleDTO chedule;
    private PatientDTO patient;

}
