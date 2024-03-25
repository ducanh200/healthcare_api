package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.entities.Clinic;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.entities.Patient;
import com.example.healthcare_api.repositories.ClinicRepository;
import com.example.healthcare_api.repositories.DepartmentRepository;
import com.example.healthcare_api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ClinicRepository clinicRepository;


    public List<Doctor> getAll(){
        return doctorRepository.findAllWithClinicAndDepartment();
    }

    public DoctorDTO createDoctor(DoctorDTO request) {
        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new IllegalArgumentException("Clinic not found with id: " + request.getClinicId()));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + request.getDepartmentId()));

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setEmail(request.getEmail());
        doctor.setPassword(request.getPassword());
        doctor.setThumbnail(request.getThumbnail());
        doctor.setPhonenumber(request.getPhonenumber());
        doctor.setClinic(clinic);
        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);

        // Create a DoctorCreateRequest object from the saved Doctor entity
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(savedDoctor.getId());
        doctorDTO.setName(savedDoctor.getName());
        doctorDTO.setEmail(savedDoctor.getEmail());
        doctorDTO.setPassword(savedDoctor.getPassword());
        doctorDTO.setThumbnail(savedDoctor.getThumbnail());
        doctorDTO.setPhonenumber(savedDoctor.getPhonenumber());
        doctorDTO.setClinicId(savedDoctor.getClinic().getId());
        doctorDTO.setDepartmentId(savedDoctor.getDepartment().getId());

        return doctorDTO;
    }


    public DoctorDTO update(Long id, DoctorDTO request) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setName(request.getName());
        doctor.setThumbnail(request.getThumbnail());
        doctor.setPhonenumber(request.getPhonenumber());

        if (!doctor.getClinic().getId().equals(request.getClinicId())) {
            Clinic newClinic = clinicRepository.findById(request.getClinicId())
                    .orElseThrow(() -> new RuntimeException("Clinic not found with id: " + request.getClinicId()));
            doctor.setClinic(newClinic);
        }

        if (!doctor.getDepartment().getId().equals(request.getDepartmentId())) {
            Department newDepartment = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + request.getDepartmentId()));
            doctor.setDepartment(newDepartment);
        }


        // Save the updated doctor
        Doctor updateDoctor = doctorRepository.save(doctor);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(updateDoctor.getId());
        doctorDTO.setName(updateDoctor.getName());
        doctorDTO.setEmail(updateDoctor.getEmail());
        doctorDTO.setPassword(updateDoctor.getPassword());
        doctorDTO.setThumbnail(updateDoctor.getThumbnail());
        doctorDTO.setPhonenumber(updateDoctor.getPhonenumber());
        doctorDTO.setClinicId(updateDoctor.getClinic().getId());
        doctorDTO.setDepartmentId(updateDoctor.getDepartment().getId());

        return doctorDTO;
    }



    public DoctorDTO findById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setThumbnail(doctor.getThumbnail());
        doctorDTO.setPhonenumber(doctor.getPhonenumber());
        doctorDTO.setClinicId(doctor.getClinic().getId());
        doctorDTO.setDepartmentId(doctor.getDepartment().getId());

        return doctorDTO;
    }

    public List<Doctor> getDoctorsByDepartmentId(Long departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }

    public void deleteDoctor(@PathVariable Long id){
        doctorRepository.deleteById(id);
        throw new RuntimeException("Doctor deleted have id :"+ id);
    }
}
