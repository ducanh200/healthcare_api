package com.example.healthcare_api.controllers;


import com.example.healthcare_api.dto.DepartmentDTO;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @PostMapping()
    public Department createDepartment(@RequestBody DepartmentDTO request){
        return departmentService.createDepartment(request);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO request){
        return departmentService.updateDepartment(id,request);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id){
        return departmentService.getById(id);
    }
}
