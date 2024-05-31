package com.rwi.e.billing.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service("emailPdfService")
public class EmailPdfService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailPdfService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmailWithAttachment(String recipientEmail, String subject, String body, String pdfFilePath) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(body);

            // Attach PDF file
            byte[] pdfBytes = Files.readAllBytes(Paths.get(pdfFilePath));
            ByteArrayResource pdfAttachment = new ByteArrayResource(pdfBytes);
            helper.addAttachment("Invoice.pdf", pdfAttachment);

            javaMailSender.send(message);

            System.out.println("EmailService.sendEmailWithAttachment() - Email with PDF attachment sent successfully to: " + recipientEmail);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendVisitAgain(String recipientEmail, String pdfFilePath) {
        String subject = "VKP Billing Service";
        String body = "Thank you for Using  our Service";
        sendEmailWithAttachment(recipientEmail, subject, body, pdfFilePath);
    }
}
