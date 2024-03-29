package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.BookingDTO;
import com.example.healthcare_api.dto.PatientDTO;
import com.example.healthcare_api.dto.ScheduleDTO;
import com.example.healthcare_api.entities.Booking;
import com.example.healthcare_api.entities.Patient;
import com.example.healthcare_api.entities.Schedule;
import com.example.healthcare_api.repositories.BookingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRespository bookingRespository;

    public List<BookingDTO> getAll(){
        List<Booking> bookings = bookingRespository.findAll();
        List<BookingDTO> bookingDTOs = new ArrayList<>();

        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setBookingAt(booking.getBookingAt());
            bookingDTO.setStatus(booking.getStatus());

            Schedule schedule = booking.getSchedule();

            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setTime(schedule.getTime());
            scheduleDTO.setStatus(schedule.getStatus());
            scheduleDTO.setClinicId(schedule.getClinic().getId());
            bookingDTO.setChedule(scheduleDTO);

            Patient patient = booking.getPatient();
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setName(patient.getName());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setPhonenumber(patient.getPhonenumber());
            patientDTO.setAddress(patient.getAddress());
            bookingDTO.setPatient(patientDTO);

            bookingDTOs.add(bookingDTO);
        }

        return bookingDTOs;
    }
}
