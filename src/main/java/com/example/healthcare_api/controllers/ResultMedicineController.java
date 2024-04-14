package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.ResultMedicineDTO;
import com.example.healthcare_api.service.ResultMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/resultmedicine")
public class ResultMedicineController {
    @Autowired
    private ResultMedicineService resultMedicineService;

    @GetMapping()
    public List<ResultMedicineDTO> getAll(){
        return resultMedicineService.getAll();
    }

    @PostMapping()
    public ResultMedicineDTO create(@RequestBody ResultMedicineDTO resultMedicineDTO){
        return resultMedicineService.create(resultMedicineDTO);
    }

    @PutMapping("/{id}")
    public ResultMedicineDTO update(@PathVariable Long id,@RequestBody ResultMedicineDTO resultMedicineDTO){
        return resultMedicineService.update(id, resultMedicineDTO);
    }

    @GetMapping("/resultId/{resultId}")
    public List<ResultMedicineDTO> getByResultId(@PathVariable Long resultId){
        return resultMedicineService.getByResultId(resultId);
    }
}