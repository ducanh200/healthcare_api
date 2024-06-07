package com.example.healthcare_api.service;

import com.example.healthcare_api.dtos.ShiftDTO;
import com.example.healthcare_api.entities.Shift;
import com.example.healthcare_api.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    public List<ShiftDTO> getAllShifts() {
        List<Shift> shifts = shiftRepository.findAll();
        List<ShiftDTO> shiftDTOs = new ArrayList<>();

        for (Shift shift : shifts) {
            ShiftDTO shiftDTO = new ShiftDTO();
            shiftDTO.setId(shift.getId());
            shiftDTO.setTime(shift.getTime());
            shiftDTO.setSession(shift.getSession());
            shiftDTOs.add(shiftDTO);
        }

        return shiftDTOs;
    }
    public ShiftDTO createShift(ShiftDTO shiftDTO) {
        Shift shift = new Shift();
        shift.setTime(shiftDTO.getTime());
        shift.setSession(shiftDTO.getSession());

        Shift savedShift = shiftRepository.save(shift);

        ShiftDTO savedShiftDTO = new ShiftDTO();
        savedShiftDTO.setId(savedShift.getId());
        savedShiftDTO.setTime(savedShift.getTime());
        savedShiftDTO.setSession(savedShift.getSession());

        return savedShiftDTO;
    }

    public ShiftDTO getShiftById(Long id) {
        Optional<Shift> optionalShift = shiftRepository.findById(id);

        if (optionalShift.isPresent()) {
            Shift shift = optionalShift.get();
            ShiftDTO shiftDTO = new ShiftDTO();
            shiftDTO.setId(shift.getId());
            shiftDTO.setTime(shift.getTime());
            shiftDTO.setSession(shift.getSession());
            return shiftDTO;
        } else {
            // Ném ra một ngoại lệ hoặc xử lý tùy thuộc vào yêu cầu của bạn
            throw new IllegalArgumentException("Không tìm thấy ca làm việc với ID: " + id);
        }
    }
    public List<ShiftDTO> getAvailableShiftsByDateAndDepartment(Date date, Long departmentId) {
        List<Shift> availableShifts = shiftRepository.findAvailableShiftsByDateAndDepartment(date, departmentId);

        return availableShifts.stream().map(shift -> {
            ShiftDTO shiftAvailabilityDTO = new ShiftDTO();
            shiftAvailabilityDTO.setId(shift.getId());
            shiftAvailabilityDTO.setTime(shift.getTime());
            shiftAvailabilityDTO.setSession(shift.getSession());
            shiftAvailabilityDTO.setAvailable(true); // Những ca làm việc này đều khả dụng
            return shiftAvailabilityDTO;
        }).collect(Collectors.toList());
    }
}
