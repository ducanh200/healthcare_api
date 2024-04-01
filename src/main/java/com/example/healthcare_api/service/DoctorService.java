package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.DepartmentDTO;
import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.repositories.DepartmentRepository;
import com.example.healthcare_api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;



    public List<DoctorDTO> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setName(doctor.getName());
            doctorDTO.setEmail(doctor.getEmail());
            doctorDTO.setPassword(doctor.getPassword());
            doctorDTO.setThumbnail(doctor.getThumbnail());
            doctorDTO.setPhonenumber(doctor.getPhonenumber());
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());

            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(doctor.getDepartment().getId());
            departmentDTO.setExpense(doctor.getDepartment().getExpense());
            departmentDTO.setName(doctor.getDepartment().getName());
            departmentDTO.setMaxBooking(doctor.getDepartment().getMaxBooking());
            departmentDTO.setDescription(doctor.getDepartment().getDescription());
            departmentDTO.setThumbnail(doctor.getDepartment().getThumbnail());

            doctorDTO.setDepartment(departmentDTO);

            doctorDTOs.add(doctorDTO);
        }

        return doctorDTOs;
    }

    public DoctorDTO createDoctor(DoctorDTO request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + request.getDepartmentId()));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setEmail(request.getEmail());
        doctor.setPassword(passwordEncoder.encode(request.getPassword()));
        doctor.setThumbnail(request.getThumbnail());
        doctor.setPhonenumber(request.getPhonenumber());
        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);

        // Create a new DoctorDTO with clinic and department information
        DoctorDTO savedDoctorDTO = new DoctorDTO();
        savedDoctorDTO.setId(savedDoctor.getId());
        savedDoctorDTO.setName(savedDoctor.getName());
        savedDoctorDTO.setEmail(savedDoctor.getEmail());
        savedDoctorDTO.setPassword(passwordEncoder.encode(savedDoctor.getPassword()));
        savedDoctorDTO.setThumbnail(savedDoctor.getThumbnail());
        savedDoctorDTO.setPhonenumber(savedDoctor.getPhonenumber());
        savedDoctorDTO.setDepartmentId(savedDoctor.getDepartment().getId()); // Set departmentId

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(savedDoctor.getDepartment().getId());
        departmentDTO.setExpense(savedDoctor.getDepartment().getExpense());
        departmentDTO.setName(savedDoctor.getDepartment().getName());
        departmentDTO.setMaxBooking(savedDoctor.getDepartment().getMaxBooking());
        departmentDTO.setDescription(savedDoctor.getDepartment().getDescription());
        departmentDTO.setThumbnail(savedDoctor.getDepartment().getThumbnail());

        savedDoctorDTO.setDepartment(departmentDTO);

        return savedDoctorDTO;
    }


    public DoctorDTO update(Long id, DoctorDTO request) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setName(request.getName());
        doctor.setThumbnail(request.getThumbnail());
        doctor.setPhonenumber(request.getPhonenumber());

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
        doctorDTO.setDepartmentId(updateDoctor.getDepartment().getId());

        return doctorDTO;
    }

    public DoctorDTO changePassword(@PathVariable Long id,@RequestBody DoctorDTO request){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setPassword(passwordEncoder.encode(request.getPassword()));

        Doctor updateDoctor = doctorRepository.save(doctor);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(updateDoctor.getId());
        doctorDTO.setName(updateDoctor.getName());
        doctorDTO.setEmail(updateDoctor.getEmail());
        doctorDTO.setPassword(passwordEncoder.encode(updateDoctor.getPassword()));
        doctorDTO.setThumbnail(updateDoctor.getThumbnail());
        doctorDTO.setPhonenumber(updateDoctor.getPhonenumber());
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
        doctorDTO.setDepartmentId(doctor.getDepartment().getId());


        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(doctor.getId());
        departmentDTO.setName(doctor.getName());
        departmentDTO.setExpense(doctor.getDepartment().getExpense());
        departmentDTO.setDescription(doctor.getDepartment().getDescription());
        departmentDTO.setThumbnail(doctor.getDepartment().getThumbnail());
        doctorDTO.setDepartment(departmentDTO);

        return doctorDTO;
    }

    public List<DoctorDTO> getByDepartmentId(Long departmentId) {
        List<Doctor> doctors = doctorRepository.findByDepartmentId(departmentId);
        List<DoctorDTO> doctorDTOs = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setName(doctor.getName());
            doctorDTO.setEmail(doctor.getEmail());
            doctorDTO.setPassword(doctor.getPassword());
            doctorDTO.setThumbnail(doctor.getThumbnail());
            doctorDTO.setPhonenumber(doctor.getPhonenumber());
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());

            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(doctor.getId());
            departmentDTO.setName(doctor.getName());
            departmentDTO.setExpense(doctor.getDepartment().getExpense());
            departmentDTO.setDescription(doctor.getDepartment().getDescription());
            departmentDTO.setThumbnail(doctor.getDepartment().getThumbnail());

            doctorDTO.setDepartment(departmentDTO);

            doctorDTOs.add(doctorDTO);
        }

        return doctorDTOs;
    }

    public void deleteDoctor(@PathVariable Long id){
        doctorRepository.deleteById(id);
        throw new RuntimeException("Doctor deleted have id :"+ id);
    }
}
