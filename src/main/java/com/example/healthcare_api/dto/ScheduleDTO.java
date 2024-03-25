package com.example.healthcare_api.dto;

import java.sql.Date;

public class ScheduleDTO {
    private Long id;
    private Date time;
    private Integer status;
    private Long clinicId;

    private ClinicDTO clinic;

    public ScheduleDTO(Long id, Date time, Integer status, Long clinicId, ClinicDTO clinic) {
        this.id = id;
        this.time = time;
        this.status = status;
        this.clinicId = clinicId;
        this.clinic = clinic;
    }

    public ScheduleDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public ClinicDTO getClinic() {
        return clinic;
    }

    public void setClinic(ClinicDTO clinic) {
        this.clinic = clinic;
    }
}
