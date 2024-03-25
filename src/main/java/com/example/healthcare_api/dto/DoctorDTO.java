package com.example.healthcare_api.dto;

import jakarta.validation.constraints.Size;

public class DoctorDTO {
    private Long id;
    @Size(min = 3,message = "Doctor's name must be at least 3 characters")
    private String name;
    private String email;
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;
    private String thumbnail;
    private String phonenumber;
    private Long clinicId;
    private Long departmentId;

    public DoctorDTO(Long id, String name, String email, String password, String thumbnail, String phonenumber, Long clinicId, Long departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.thumbnail = thumbnail;
        this.phonenumber = phonenumber;
        this.clinicId = clinicId;
        this.departmentId = departmentId;
    }

    public DoctorDTO() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
