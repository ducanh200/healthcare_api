package com.example.healthcare_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double expense;
    private String description;
    private String thumbnail;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Clinic> clinics;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Device> devices;

}
