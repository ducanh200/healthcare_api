package com.example.healthcare_api.dto;

import jakarta.validation.constraints.Size;

public class DeviceDTO {
    private Long id;
    @Size(min = 3,message = "Doctor's name must be at least 3 characters")
    private String name;
    private String description;
    private Double expense;
    private Long departmentId;

    public DeviceDTO(Long id, String name, String description, Double expense, Long departmentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expense = expense;
        this.departmentId = departmentId;
    }

    public DeviceDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
