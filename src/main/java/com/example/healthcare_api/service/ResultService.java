package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.BookingDTO;
import com.example.healthcare_api.dto.DoctorDTO;
import com.example.healthcare_api.dto.ResultDTO;
import com.example.healthcare_api.entities.Booking;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.entities.Result;
import com.example.healthcare_api.repositories.BookingRepository;
import com.example.healthcare_api.repositories.DoctorRepository;
import com.example.healthcare_api.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DoctorRepository doctorRepository;


    public List<ResultDTO> getAllResults() {
        List<Result> results = resultRepository.findAll();
        List<ResultDTO> resultDTOs = new ArrayList<>(results.size());

        results.forEach(result -> {
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setId(result.getId());
            resultDTO.setRequestTest(result.getRequestTest());
            resultDTO.setExpense(result.getExpense());
            resultDTO.setDiagnoseEnd(result.getDiagnoseEnd());
            resultDTO.setBookingId(result.getBooking().getId());
            resultDTO.setDoctorId(result.getDoctor().getId());

            BookingDTO bookingDTO = new BookingDTO();
            Booking booking = result.getBooking();
            bookingDTO.setId(booking.getId());
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setStatus(booking.getStatus());
            bookingDTO.setBookingAt(booking.getBookingAt());
            bookingDTO.setDepartmentId(booking.getDepartment().getId());
            bookingDTO.setPatientId(booking.getPatient().getId());
            bookingDTO.setShiftId(booking.getShift().getId());
            resultDTO.setBooking(bookingDTO);

            DoctorDTO doctorDTO = new DoctorDTO();
            Doctor doctor = result.getDoctor();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setName(doctor.getName());
            doctorDTO.setPhonenumber(doctor.getPhonenumber());
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());
            resultDTO.setDoctor(doctorDTO);

            resultDTOs.add(resultDTO);
        });

        return resultDTOs;
    }

    public ResultDTO createResult(@RequestBody ResultDTO request) {
        // Tìm phòng ban tương ứng
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + request.getBookingId()));

        // Tìm đơn thuốc tương ứng
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found with id: " + request.getDoctorId()));

        // Tạo một đối tượng Result mới
        Result result = new Result();
        result.setExpense(request.getExpense());
        result.setBooking(booking);
        result.setDoctor(doctor);

        // Lưu đối tượng Result mới vào cơ sở dữ liệu
        Result savedResult = resultRepository.save(result);

        // Tạo DTO từ đối tượng Result đã lưu
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(savedResult.getId());
        resultDTO.setExpense(savedResult.getExpense());
        resultDTO.setBookingId(savedResult.getBooking().getId());
        resultDTO.setDoctorId(savedResult.getDoctor().getId());

        return resultDTO;
    }
}
