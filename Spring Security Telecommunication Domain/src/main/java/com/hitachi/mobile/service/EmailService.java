package com.hitachi.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset - Hitachi Mobile SIM Portal");
        message.setText("Click the following link to reset your password: " +
                "http://localhost:8080/reset-password?token=" + token);

        mailSender.send(message);
    }

    public void sendWelcomeEmail(String to, String customerName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to Hitachi Mobile SIM Portal");
        message.setText("Dear " + customerName + ",\n\n" +
                "Welcome to Hitachi Mobile SIM Portal! Your account has been successfully created.\n\n" +
                "Best regards,\nHitachi Mobile Team");

        mailSender.send(message);
    }

    public void sendSimActivationNotification(String to, String simNumber, String serviceNumber) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SIM Activation Successful - Hitachi Mobile");
        message.setText("Dear Customer,\n\n" +
                "Your SIM has been successfully activated!\n" +
                "SIM Number: " + simNumber + "\n" +
                "Service Number: " + serviceNumber + "\n\n" +
                "Thank you for choosing Hitachi Mobile.\n\n" +
                "Best regards,\nHitachi Mobile Team");

        mailSender.send(message);
    }
}