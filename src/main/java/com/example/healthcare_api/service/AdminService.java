package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Admin;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public Admin createAdmin(@RequestBody Admin admin){
        return adminRepository.save(admin);
    }

    public void deleteAdmin(@PathVariable Long id){
        adminRepository.deleteById(id);
    }
}
