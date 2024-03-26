package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.AdminDTO;
import com.example.healthcare_api.entities.Admin;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public AdminDTO create(AdminDTO adminDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Admin admin = new Admin();
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        Admin savedAdmin = adminRepository.save(admin);

        AdminDTO savedAdminDTO = new AdminDTO();
        savedAdminDTO.setId(savedAdmin.getId());
        savedAdminDTO.setName(savedAdmin.getName());
        savedAdminDTO.setEmail(savedAdmin.getEmail());
        savedAdminDTO.setPassword(savedAdmin.getPassword());

        return savedAdminDTO;
    }

    public AdminDTO update(Long id, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        existingAdmin.setName(adminDTO.getName());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        existingAdmin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        Admin updatedAdmin = adminRepository.save(existingAdmin);

        AdminDTO updateAdminDTO = new AdminDTO();
        updateAdminDTO.setId(updatedAdmin.getId());
        updateAdminDTO.setName(updatedAdmin.getName());
        updateAdminDTO.setEmail(updatedAdmin.getEmail());
        updateAdminDTO.setPassword(updatedAdmin.getPassword());
        return updateAdminDTO;
    }
    public void deleteAdmin(@PathVariable Long id){
        adminRepository.deleteById(id);
    }
}
