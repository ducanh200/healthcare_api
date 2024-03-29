package com.example.healthcare_api.dto;

import lombok.Data;

import java.sql.Date;
@Data
public class ScheduleDTO {
    private Long id;
    private Date time;
    private Integer status;
    private Long clinicId;

    private ClinicDTO clinic;

}
