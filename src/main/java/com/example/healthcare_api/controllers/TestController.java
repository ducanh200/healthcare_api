package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.TestDTO;
import com.example.healthcare_api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping()
    public TestDTO createTest(@RequestBody TestDTO testDTO){
        return testService.createTest(testDTO);
    }
}
