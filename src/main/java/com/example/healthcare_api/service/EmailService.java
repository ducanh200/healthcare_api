package com.example.healthcare_api.service;

import com.example.healthcare_api.dtos.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    public void sendSimpleMessage(@RequestBody EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getMessage());
        emailSender.send(message);
    }
}
