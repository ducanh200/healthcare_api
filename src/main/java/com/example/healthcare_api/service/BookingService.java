package com.example.healthcare_api.service;

import com.example.healthcare_api.entities.Booking;
import com.example.healthcare_api.repositories.BookingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRespository bookingRespository;

    public List<Booking> getAll(){
        return bookingRespository.findAll();
    }
}
