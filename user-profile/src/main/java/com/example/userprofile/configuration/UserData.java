package com.example.userprofile.configuration;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserData {
    private String emailId;
    private String name;
    private String mobileNo;
    private Address address;
}
