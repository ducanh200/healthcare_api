package com.example.healthcare_api.dto;

import lombok.Data;

@Data
public class PrescriptionDTO {
    private Long id;
    private String description;
    private Long doctorId;
    private DoctorDTO doctor;
}
