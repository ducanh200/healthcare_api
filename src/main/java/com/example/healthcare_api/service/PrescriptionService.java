package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.dto.PrescriptionDTO;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.entities.Prescription;
import com.example.healthcare_api.repositories.DoctorRepository;
import com.example.healthcare_api.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<PrescriptionDTO> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        List<PrescriptionDTO> prescriptionDTOs = new ArrayList<>(prescriptions.size());

        for (Prescription prescription : prescriptions) {
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
            prescriptionDTO.setId(prescription.getId());
            prescriptionDTO.setDescription(prescription.getDescription());
            prescriptionDTO.setDoctorId(prescription.getDoctor().getId());

            DoctorDTO doctorDTO = new DoctorDTO();
            Doctor doctor = prescription.getDoctor();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setName(doctor.getName());
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());
            doctorDTO.setPhonenumber(doctor.getPhonenumber());

            prescriptionDTO.setDoctor(doctorDTO);

            prescriptionDTOs.add(prescriptionDTO);
        }

        return prescriptionDTOs;
    }

    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO request) {
        // Tìm bác sĩ tương ứng
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + request.getDoctorId()));

        // Tạo một đối tượng Prescription mới
        Prescription prescription = new Prescription();
        prescription.setDescription(request.getDescription());
        prescription.setDoctor(doctor);


        // Lưu đối tượng Prescription mới vào cơ sở dữ liệu
        Prescription savedPrescription = prescriptionRepository.save(prescription);

        // Tạo DTO từ đối tượng Prescription đã lưu
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setId(savedPrescription.getId());
        prescriptionDTO.setDescription(savedPrescription.getDescription());
        prescriptionDTO.setDoctorId(savedPrescription.getDoctor().getId());

        return prescriptionDTO;
    }
}
