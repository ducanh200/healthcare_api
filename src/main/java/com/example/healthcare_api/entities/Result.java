package com.example.healthcare_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_test")
    private String requestTest;

    private Double expense;

    @Column(name = "diagnose_end")
    private String diagnoseEnd;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany(targetEntity = Medicine.class,fetch = FetchType.EAGER)
    @JoinTable(name = "result_medicine",
            joinColumns = @JoinColumn(name = "result_id "),
            inverseJoinColumns = @JoinColumn(name = "medicine_id ")
    )
    private List<Medicine> medicines;

    @JsonIgnore
    @OneToMany(mappedBy = "result")
    private List<Test> tests;



}
