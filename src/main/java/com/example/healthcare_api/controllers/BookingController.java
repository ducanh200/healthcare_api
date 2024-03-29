package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dto.BookingDTO;
import com.example.healthcare_api.entities.Booking;
import com.example.healthcare_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public List<BookingDTO> getAllBooking(){
        return bookingService.getAll();
    }
}
