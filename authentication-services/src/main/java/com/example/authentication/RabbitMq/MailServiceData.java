package com.example.authentication.RabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailServiceData {
    private String to;
    private String subject;
    private String message;
}
