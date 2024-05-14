package com.example.healthcare_api.controllers;

import com.example.healthcare_api.dtos.BookingDTO;
import com.example.healthcare_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    //dùng form data
    @GetMapping("/date")
    public List<BookingDTO> getBookingsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return bookingService.getByDate(date);
    }

    @GetMapping("/dateRange")
    public List<BookingDTO> getBookingsByDateRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                   @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return bookingService.getByDateRange(startDate, endDate);
    }
}
