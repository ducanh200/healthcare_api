package com.example.healthcare_api.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    @Size(min = 3,message = "Department's name must be at least 3 characters")
    private String name;
    private Double expense;
    private String description;
    private String thumbnail;

    public DepartmentDTO(Long id, String name, Double expense, String description, String thumbnail) {
    }

    public DepartmentDTO() {

    }
}
