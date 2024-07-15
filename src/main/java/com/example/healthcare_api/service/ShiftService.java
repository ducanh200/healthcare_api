package com.example.healthcare_api.service;

import com.example.healthcare_api.dtos.ShiftDTO;
import com.example.healthcare_api.entities.Booking;
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
    public List<ShiftDTO> getShiftsByDateAndDepartment(Date date, Long departmentId) {
        // Lấy danh sách tất cả các Shift
        List<Shift> allShifts = shiftRepository.findAll();

        // Lấy danh sách các Shift đã được đặt lịch cho ngày và phòng ban cụ thể
        List<Booking> bookings = shiftRepository.findByDateAndDepartment(date, departmentId);

        // Lấy maxBooking của phòng ban từ ShiftRepository
        int maxBooking = shiftRepository.findMaxBookingByDepartmentId(departmentId);

        return allShifts.stream().map(shift -> {
            ShiftDTO shiftDTO = new ShiftDTO();
            shiftDTO.setId(shift.getId());
            shiftDTO.setTime(shift.getTime());
            shiftDTO.setSession(shift.getSession());

            // Đếm số lượng Booking của Shift hiện tại
            long bookingCount = bookings.stream()
                    .filter(booking -> booking.getShift().getId().equals(shift.getId()))
                    .count();


            // Đặt status
            if (bookingCount >= maxBooking) {
                shiftDTO.setStatus(1);
            }

            return shiftDTO;
        }).collect(Collectors.toList());
    }
}
