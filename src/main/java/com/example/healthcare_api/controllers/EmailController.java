package com.example.healthcare_api.controllers;

import com.example.healthcare_api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/send_email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping()
    public String sendEmail(@RequestBody String to, @RequestBody String subject) {
        emailService.sendSimpleMessage(to,subject);
        return "Email sent successfully";
    }
}
