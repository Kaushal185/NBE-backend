package com.grs.angproject.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        System.out.println("Starting email sending process");

        if (!isValidEmailAddress(to)) {
            System.err.println("Invalid email address: " + to);
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("translator-application@oracle.com");
        System.out.println("This is from DB" +to);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
            System.out.println("Email successfully sent to: " + to);
        } catch (MailSendException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while sending email: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Ended email sending process");
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
