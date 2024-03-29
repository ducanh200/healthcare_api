package com.example.healthcare_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;
    private Date birthday;
    private String phonenumber;
    private String address;
    private String city;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Booking> bookings;

}
