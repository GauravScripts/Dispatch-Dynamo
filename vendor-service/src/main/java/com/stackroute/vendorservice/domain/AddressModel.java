package com.stackroute.vendorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressModel {
    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
