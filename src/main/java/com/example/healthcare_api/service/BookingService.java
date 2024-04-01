package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.BookingDTO;
import com.example.healthcare_api.dto.DepartmentDTO;
import com.example.healthcare_api.dto.PatientDTO;
import com.example.healthcare_api.dto.ShiftDTO;
import com.example.healthcare_api.entities.Booking;
import com.example.healthcare_api.entities.Department;
import com.example.healthcare_api.entities.Patient;
import com.example.healthcare_api.entities.Shift;
import com.example.healthcare_api.repositories.BookingRespository;
import com.example.healthcare_api.repositories.DepartmentRepository;
import com.example.healthcare_api.repositories.PatientRepository;
import com.example.healthcare_api.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRespository bookingRespository;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    public List<BookingDTO> getAll(){
        List<Booking> bookings = bookingRespository.findAll();
        List<BookingDTO> bookingDTOs = new ArrayList<>();

        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setBookingAt(booking.getBookingAt());
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setStatus(booking.getStatus());



            bookingDTO.setPatientId(booking.getPatient().getId());
            bookingDTO.setDepartmentId(booking.getDepartment().getId());
            bookingDTO.setShiftId(booking.getShift().getId());

            Patient patient = booking.getPatient();
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setName(patient.getName());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setPhonenumber(patient.getPhonenumber());
            patientDTO.setAddress(patient.getAddress());

            bookingDTO.setPatient(patientDTO);

            Department department = booking.getDepartment();
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            departmentDTO.setExpense(department.getExpense());
            departmentDTO.setMaxBooking(department.getMaxBooking());

            bookingDTO.setDepartment(departmentDTO);

            Shift shift = booking.getShift();
            ShiftDTO shiftDTO = new ShiftDTO();
            shiftDTO.setId(shift.getId());
            shiftDTO.setTime(shift.getTime());
            shiftDTO.setSession(shift.getSession());

            bookingDTO.setShift(shiftDTO);

            bookingDTOs.add(bookingDTO);
        }

        return bookingDTOs;
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();

        booking.setBookingAt(Timestamp.valueOf(LocalDateTime.now()));
        booking.setStatus(1);
        booking.setDate(bookingDTO.getDate());


        Patient patient = patientRepository.findById(bookingDTO.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + bookingDTO.getPatientId()));
        booking.setPatient(patient);

        Department department = departmentRepository.findById(bookingDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + bookingDTO.getDepartmentId()));
        booking.setDepartment(department);

        // Find and set shift
        Shift shift = shiftRepository.findById(bookingDTO.getShiftId())
                .orElseThrow(() -> new IllegalArgumentException("Shift not found with id: " + bookingDTO.getShiftId()));
        booking.setShift(shift);

        Booking savedBooking = bookingRespository.save(booking);

        BookingDTO savedBookingDTO = new BookingDTO();
        savedBookingDTO.setId(savedBooking.getId());
        savedBookingDTO.setBookingAt(savedBooking.getBookingAt());
        savedBookingDTO.setStatus(savedBooking.getStatus());
        savedBookingDTO.setDate(savedBooking.getDate());
        savedBookingDTO.setPatientId(savedBooking.getPatient().getId());
        savedBookingDTO.setDepartmentId(savedBooking.getDepartment().getId());
        savedBookingDTO.setShiftId(savedBooking.getShift().getId());

        return savedBookingDTO;
    }

}
