package com.example.healthcare_api.controllers;


import com.example.healthcare_api.dto.DepartmentDTO;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v3/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Department createDepartment(@RequestParam("name") String name,
                                       @RequestParam("expense") Double expense,
                                       @RequestParam("maxBooking") Integer maxBooking,
                                       @RequestParam("description") String description,
                                       @RequestParam("thumbnail") MultipartFile file) {
        DepartmentDTO request = new DepartmentDTO();
        request.setName(name);
        request.setExpense(expense);
        request.setMaxBooking(maxBooking);
        request.setDescription(description);

        return departmentService.createDepartment(request, file);
    }

    @PutMapping(consumes = {"multipart/form-data"} , value = "/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO request, @RequestParam(value = "file" ) MultipartFile file) {
        return departmentService.updateDepartment(id, request, file);
    }
    @CrossOrigin(origins = "*")

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id){
        return departmentService.getById(id);
    }
}
