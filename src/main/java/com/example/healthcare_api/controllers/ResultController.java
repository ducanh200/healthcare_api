package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dtos.ResultDTO;
import com.example.healthcare_api.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/results")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping()
    public List<ResultDTO> getAll(){
        return resultService.getAllResults();
    }

    @PostMapping()
    public ResultDTO createResult(@RequestBody ResultDTO request){
        return resultService.createResult(request);
    }
}
