package com.example.healthcare_api.dto;

import lombok.Data;

@Data
public class ClinicDTO {
    private Long id;
    private String name;
    private Long departmentId;

    private DepartmentDTO department;

    public ClinicDTO(Long id, String name, Long id1) {
    }

    public ClinicDTO() {

    }
}
