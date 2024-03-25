package com.example.healthcare_api.dto;

import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    private Long id;
    @Size(min = 3,message = "Department's name must be at least 3 characters")
    private String name;
    private Double expense;
    private String description;
    private String thumbnail;

    public DepartmentDTO(Long id, String name, Double expense, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.expense = expense;
        this.description = description;
        this.thumbnail = thumbnail;
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

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
