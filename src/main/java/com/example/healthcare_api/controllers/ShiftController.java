package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.ShiftDTO;
import com.example.healthcare_api.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @GetMapping()
    public List<ShiftDTO> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @PostMapping("")
    public ShiftDTO createShift(@RequestBody ShiftDTO shiftDTO) {
        return shiftService.createShift(shiftDTO);
    }

    @GetMapping("/{id}")
    public ShiftDTO getById(@PathVariable Long id){
        return shiftService.getShiftById(id);
    }
}
