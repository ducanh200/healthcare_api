package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.PatientDTO;
import com.example.healthcare_api.entities.Patient;
import com.example.healthcare_api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    public Patient createPatient(@RequestBody PatientDTO request){
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setPassword(request.getPassword());
        patient.setGender(request.getGender());
        patient.setBirthday(request.getBirthday());
        patient.setPhonenumber(request.getPhonenumber());
        patient.setAddress(request.getAddress());
        patient.setCity(request.getCity());

        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, PatientDTO request) {
        Patient patient = getById(id);

        patient.setName(request.getName());
        patient.setGender(request.getGender());
        patient.setBirthday(request.getBirthday());
        patient.setPhonenumber(request.getPhonenumber());
        patient.setAddress(request.getAddress());
        patient.setCity(request.getCity());

        return patientRepository.save(patient);
    }

    public void deletePatient(@PathVariable Long id){
        patientRepository.deleteById(id);
        throw new RuntimeException("Patient has been deleted");
    }

    public List<Patient> findByName(String name){
        return patientRepository.findAllByNameContaining(name);
    }

    public Patient getById(Long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
