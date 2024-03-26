package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.DeviceDTO;
import com.example.healthcare_api.dto.ScheduleDTO;
import com.example.healthcare_api.entities.Device;
import com.example.healthcare_api.entities.Schedule;
import com.example.healthcare_api.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;;

    @GetMapping()
    public List<ScheduleDTO> getAllSchedule(){
        return scheduleService.getAll();
    }

    @PostMapping()
    public ScheduleDTO createSchedule(@RequestBody @Valid ScheduleDTO request) {
        return scheduleService.createSchedule(request);
    }

    @PutMapping("/{id}")
    public ScheduleDTO updateSchedule(@PathVariable Long id ,@RequestBody @Valid ScheduleDTO request) {
        return scheduleService.updateSchedule(id,request);
    }
    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
    }
}
