package com.example.authentication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private String emailId;
    private String name;

}
