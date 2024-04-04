package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.DepartmentDTO;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department createDepartment(DepartmentDTO request, MultipartFile file) {
        Department department = new Department();

        department.setName(request.getName());
        department.setExpense(request.getExpense());
        department.setThumbnail(request.getThumbnail());
        department.setDescription(request.getDescription());

            String fileName = file.getOriginalFilename();
            String filePath = "assets/img/department/" + fileName;
            department.setThumbnail(filePath);

        return departmentRepository.save(department);
    }
    public Department updateDepartment(Long id, DepartmentDTO request, MultipartFile file) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(request.getName());
        department.setExpense(request.getExpense());
        department.setThumbnail(request.getThumbnail());
        department.setDescription(request.getDescription());

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = "/assets/img/" + fileName;
            department.setThumbnail(filePath);
        }

        return departmentRepository.save(department);
    }

    public void deleteDepartment(@PathVariable Long id){
        departmentRepository.deleteById(id);
    }

    public Department getById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Department not found"));
    }
}
