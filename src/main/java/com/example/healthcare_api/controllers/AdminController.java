package com.example.healthcare_api.controllers;

import com.example.healthcare_api.entities.Admin;
import com.example.healthcare_api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public List<Admin> getAllAdmin(){
        return adminService.getAll();
    }
    @PostMapping()
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }
}
