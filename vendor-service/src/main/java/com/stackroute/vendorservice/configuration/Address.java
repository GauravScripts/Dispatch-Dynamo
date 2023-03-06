package com.stackroute.vendorservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
