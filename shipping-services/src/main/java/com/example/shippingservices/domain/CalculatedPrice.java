package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedPrice {
    private String vendorId;
    private String vendorName;
    private String vendorContact;
    private String modeOfTransport;
    private Double price;



}
