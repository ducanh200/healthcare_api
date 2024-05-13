package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dtos.BookingDTO;
import com.example.healthcare_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public List<BookingDTO> getAllBooking(){
        return bookingService.getAll();
    }
    @PostMapping()
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/updateStatus/{id}")
    public BookingDTO updateStatus(@PathVariable Long id){
        return bookingService.updateStatus(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/cancelBooking/{id}")
    public BookingDTO cancelBooking(@PathVariable Long id){
        return bookingService.cancelBooking(id);
    }
    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id){
        return bookingService.getById(id);
    }

    @GetMapping("getByPatientId/{id}")
    public List<BookingDTO> getByPatientId(@PathVariable Long id){
        return bookingService.getByPatientId(id);
    }

    @GetMapping("getByDepartmentId/{id}")
    public List<BookingDTO> getByDepartmentId(@PathVariable Long id){
        return bookingService.getByDepartmentId(id);
    }
}
