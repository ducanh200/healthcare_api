package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dtos.DepartmentDTO;
import com.example.healthcare_api.dtos.TestDTO;
import com.example.healthcare_api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v3/tests")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping()
    public List<TestDTO> getAll(){
        return testService.getAllTests();
    }
    @PostMapping(consumes = {"multipart/form-data"})
    public TestDTO createTest(@RequestParam("diagnose") String diagnose,
                              @RequestParam("expense") Double expense,
                              @RequestParam("deviceId") Long deviceId ,
                              @RequestParam("doctorId") Long doctorId ,
                              @RequestParam("resultId") Long resultId ,
                              @RequestParam("thumbnail") MultipartFile file){
        TestDTO request = new TestDTO();
        request.setDiagnose(diagnose);
        request.setExpense(expense);
        request.setDeviceId(deviceId);
        request.setResultId(resultId);
        request.setDoctorId(doctorId);

        try {
            return testService.createTest(request, file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public TestDTO updateTest(@PathVariable Long id,
                                              @RequestParam("diagnose") String diagnose,
                                              @RequestParam("expense") Double expense,
                                              @RequestParam("deviceId") Long deviceId,
                                              @RequestParam("resultId") Long resultId,
                                              @RequestParam(value = "thumbnail", required = false) MultipartFile file) {
        TestDTO request = new TestDTO();
        request.setDiagnose(diagnose);
        request.setExpense(expense);
        request.setDeviceId(deviceId);
        request.setResultId(resultId);

        try {
            return testService.updateTest(id,request,file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public TestDTO getById(@PathVariable Long id){
        return testService.getById(id);
    }
}
