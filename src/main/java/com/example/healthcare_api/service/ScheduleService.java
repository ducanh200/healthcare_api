package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.ClinicDTO;
import com.example.healthcare_api.dto.DeviceDTO;
import com.example.healthcare_api.dto.ScheduleDTO;
import com.example.healthcare_api.entities.Clinic;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.entities.Device;
import com.example.healthcare_api.entities.Schedule;
import com.example.healthcare_api.repositories.ClinicRepository;
import com.example.healthcare_api.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleRepository.findAllWithClinic();
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : schedules) {
            Clinic clinic = schedule.getClinic(); // Ensure Clinic is eagerly fetched
            ClinicDTO clinicDTO = new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getDepartment().getId()); // Assuming ClinicDTO constructor takes id, name, and departmentId

            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(schedule.getId());
            dto.setTime(schedule.getTime());
            dto.setStatus(schedule.getStatus());
            dto.setClinicId(schedule.getClinic().getId());
            dto.setClinic(clinicDTO);

            scheduleDTOs.add(dto);
        }

        return scheduleDTOs;
    }


    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO request){
        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new IllegalArgumentException("Clinic not found with id: " + request.getClinicId()));

        Schedule schedule = new Schedule();
        schedule.setTime(request.getTime());
        schedule.setStatus(request.getStatus());
        schedule.setClinic(clinic);

        Schedule scheduleSaved = scheduleRepository.save(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(scheduleSaved.getId());
        scheduleDTO.setTime(scheduleSaved.getTime());
        scheduleDTO.setStatus(scheduleSaved.getStatus());
        scheduleDTO.setClinicId(scheduleSaved.getClinic().getId());

        return scheduleDTO;
    }

    public ScheduleDTO updateSchedule(Long id, ScheduleDTO request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        schedule.setTime(request.getTime());
        schedule.setStatus(request.getStatus());


        if (!schedule.getClinic().getId().equals(request.getClinicId())) {
            Clinic newClinic = clinicRepository.findById(request.getClinicId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + request.getClinicId()));
            schedule.setClinic(newClinic);
        }


        Schedule updateSchedule= scheduleRepository.save(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(updateSchedule.getId());
        scheduleDTO.setTime(updateSchedule.getTime());
        scheduleDTO.setStatus(updateSchedule.getStatus());
        scheduleDTO.setClinicId(updateSchedule.getClinic().getId());

        return scheduleDTO;
    }

    public void deleteSchedule(@PathVariable Long id){
        scheduleRepository.deleteById(id);
        throw new RuntimeException("Deleted schedule have id : "+id);
    }
}
