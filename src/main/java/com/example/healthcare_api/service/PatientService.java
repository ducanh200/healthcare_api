package com.example.healthcare_api.service;

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

    public Patient createPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> existingPatientOptional = patientRepository.findById(id);
        if (existingPatientOptional.isPresent()) {
            Patient existingPatient = existingPatientOptional.get();

            patient.setEmail(existingPatient.getEmail());
            patient.setPassword(existingPatient.getPassword());

            existingPatient.setName(patient.getName());
            existingPatient.setGender(patient.getGender());
            existingPatient.setBirthday(patient.getBirthday());
            existingPatient.setPhonenumber(patient.getPhonenumber());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setCity(patient.getCity());

            return patientRepository.save(existingPatient);
        } else {
            return null;
        }
    }

    public void deletePatient(@PathVariable Long id){
        patientRepository.deleteById(id);
    }

    public List<Patient> findByName(String name){
        return patientRepository.findAllByNameContaining(name);
    }

    public Patient getById(Long id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        try {
            return patientOptional.get();
        }catch (Exception e){
            return null;
        }
    }
}
