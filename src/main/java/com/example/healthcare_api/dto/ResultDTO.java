package com.example.healthcare_api.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private Long id;
    private Double expense;
    private Long bookingId;
    private Long prescriptionId;

    private BookingDTO booking;
    private PrescriptionDTO prescription;
}
