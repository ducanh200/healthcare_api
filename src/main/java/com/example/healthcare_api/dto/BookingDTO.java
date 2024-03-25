package com.example.healthcare_api.dto;

import java.sql.Date;

public class BookingDTO {
    private Long id;
    private Date bookingAt;
    private Integer status;
    private Integer scheduleId;
    private Integer patientId;

    public BookingDTO(Long id, Date bookingAt, Integer status, Integer scheduleId, Integer patientId) {
        this.id = id;
        this.bookingAt = bookingAt;
        this.status = status;
        this.scheduleId = scheduleId;
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingAt() {
        return bookingAt;
    }

    public void setBookingAt(Date bookingAt) {
        this.bookingAt = bookingAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
