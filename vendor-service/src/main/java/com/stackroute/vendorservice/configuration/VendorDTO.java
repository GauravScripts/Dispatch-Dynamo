package com.stackroute.vendorservice.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class VendorDTO {
    private String emailId;
    private String name;
    private Address address;
    private String companyName;
    private String officeContact;
}
