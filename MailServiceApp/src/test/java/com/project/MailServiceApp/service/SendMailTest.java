package com.project.MailServiceApp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.MailServiceApp.mailSender.SendMail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class SendMailTest {

    @Autowired
    private SendMail sendMail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSendMail() {
        // Arrange
        String receiver = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Act
        boolean result = sendMail.send(receiver, subject, body);

        // Assert
        assertTrue(result);
    }
}
