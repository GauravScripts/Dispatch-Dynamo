package com.stackroute.vendorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedData {
    private String name;
    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String companyName;
    private String officeContact;
}
