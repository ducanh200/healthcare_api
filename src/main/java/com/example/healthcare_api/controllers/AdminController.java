package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.AdminDTO;
import com.example.healthcare_api.entities.Admin;
import com.example.healthcare_api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public List<Admin> getAllAdmin(){
        return adminService.getAll();
    }
    @PostMapping()
    public AdminDTO createAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.create(adminDTO);
    }

    @PutMapping("/{id}")
    public AdminDTO updateAdmin(@PathVariable Long id,@RequestBody AdminDTO adminDTO){
        return adminService.update(id, adminDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }
}
