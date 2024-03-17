package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department createDepartment(@RequestBody Department department){
        return  departmentRepository.save(department);
    }
    public Department updateDepartment(@PathVariable Long id,@RequestBody Department department){
        return departmentRepository.findById(id).map(d->{
            d.setName(department.getName());
            d.setExpense(department.getExpense());
            d.setDescription(department.getDescription());
            d.setThumbnail(department.getThumbnail());
            return departmentRepository.save(department);
        }).orElseGet(()->{
             department.setId(id);
             return departmentRepository.save(department);
        });
    }

    public void deleteDepartment(@PathVariable Long id){
        departmentRepository.deleteById(id);
    }

    public Department getById(@PathVariable Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        try {
            return departmentOptional.get();
        } catch (Exception e) {
            return null;
        }
    }
}
